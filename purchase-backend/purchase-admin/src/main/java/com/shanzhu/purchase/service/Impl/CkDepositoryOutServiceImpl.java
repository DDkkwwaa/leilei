package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.CkDepositoryDao;
import com.shanzhu.purchase.dao.CkStockDao;
import com.shanzhu.purchase.dao.JxPurchaseExitDao;
import com.shanzhu.purchase.mapper.CkmdDepositoryOutMapper;
import com.shanzhu.purchase.mapper.JxmdPurchaseExitMapper;
import com.shanzhu.purchase.model.*;
import com.shanzhu.purchase.service.CkDepositoryOutService;
import com.shanzhu.purchase.util.UUidUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库清单 service
 */
@Service
public class CkDepositoryOutServiceImpl implements CkDepositoryOutService {

    @Resource
    private CkmdDepositoryOutMapper depositoryOutMapper;

    @Resource
    private CkStockDao stockDao; //库存

    @Resource
    private CkDepositoryDao depositoryDao; //仓库

    @Resource
    private JxmdPurchaseExitMapper purchaseExitMapper; //采购退货

    @Resource
    private JxPurchaseExitDao purchaseExitDao; //自定义采购退货


    @Override
    public int create(CkmdDepositoryOut depositoryOut) {
        depositoryOut.setOutId(Long.valueOf(UUidUtils.uuid()));
        depositoryOut.setCreateDate(LocalDateTime.now());
        depositoryOut.setStatus(1);
        // 计算
        // （1）小数计算对精度无要求时，使用float节省时间。
        // （2）如果有精度要求，用BigDecimal类处理（初始化必须使用字符串，因为用数值初始化会得到近似值，不准确），
        // 然后设置保留位数和 舍入法（half_up四舍五入，half_even银行家，half_down向下取整）
        BigDecimal priceTotal = null;
        BigDecimal shopPrice = depositoryOut.getShopPrice();  //商品单价
        Long shopNumber = depositoryOut.getShopNumber();    //商品数量
        if (shopPrice != null && shopNumber != null) {
            BigDecimal ShopNumber1 = new BigDecimal(shopNumber);
            priceTotal = ShopNumber1.multiply(shopPrice);  //商品数量乘商品价格
            depositoryOut.setTotalPrice(priceTotal);
        }

        return depositoryOutMapper.insert(depositoryOut);
    }

    @Override
    public int update(CkmdDepositoryOut depositoryOut) {
        BigDecimal priceTotal;
        BigDecimal shopPrice = depositoryOut.getShopPrice();
        Long shopNumber = depositoryOut.getShopNumber();
        if (shopPrice != null && shopNumber != null) {
            BigDecimal ShopNumber1 = new BigDecimal(shopNumber);
            //商品数量乘商品价格
            priceTotal = ShopNumber1.multiply(shopPrice);
            depositoryOut.setTotalPrice(priceTotal);
        }
        return depositoryOutMapper.updateByPrimaryKeySelective(depositoryOut);
    }

    @Override
    public int delete(Long id) {
        return depositoryOutMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List list() {
        return depositoryOutMapper.selectByExample(new CkmdDepositoryOutExample());
    }

    @Override
    public List<CkmdDepositoryOut> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CkmdDepositoryOutExample example = new CkmdDepositoryOutExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andShopNameLike("%" + keyword + "%");
        }
        return depositoryOutMapper.selectByExample(example);
    }

    @Override
    public int addOrUpdateDepositoryOut(CkmdDepositoryOut depositoryOut) {
        // 判断 编号是否存在，如果存在就走更新的逻辑否则新增数据
        int res = 0;
        if (depositoryOut.getId() != null && depositoryOut.getId() != 0) {
            // 表示更新操作
            res = this.update(depositoryOut);
        } else {
            res = this.create(depositoryOut);
        }
        return res;
    }


    /**
     * 商品 审核后出库
     * 0. 库存列表是否有充足的库存？ 无则返回  有则执行1
     * 1. 判断是否重复出库， 否 则执行2
     * 2. 出库 且 增加对应的仓库容量，及 减少对应的库存容量
     */
    @Override
    public int checkById(Long id) {
        int result = 0;
        if (id == null) return result;
        CkmdDepositoryOut upDepositoryOut = new CkmdDepositoryOut();
        CkmdDepositoryOut selectDepositoryOut = depositoryOutMapper.selectByPrimaryKey(id);  //原来的状态
        String shopName = selectDepositoryOut.getShopName();  //商品名
        String depositoryName = selectDepositoryOut.getDepository();  //获取仓库名
        Long shopNumber = selectDepositoryOut.getShopNumber();   //获取出库的数量
        Long sourceNumber = selectDepositoryOut.getSourceNumber(); //获取采购编号
        List<CkmdStock> stockList = stockDao.getStockByTwoName(shopName, depositoryName);
        CkmdStock stockCheck = stockList.get(0);
        if (stockCheck != null) {

        }
        //如果库存少于需要出库的 直接返回
        if (stockCheck.getQuantity() < shopNumber) {
            return result;
        }

        if (selectDepositoryOut.getStatus() == 1) {  //原来还是未出库状态则设置 出库时间
            upDepositoryOut.setDate(LocalDateTime.now());
        }
        upDepositoryOut.setOutInspection(0);      //审核  状态
        upDepositoryOut.setStatus(0);            //出库   状态
        //更新的条件
        CkmdDepositoryOutExample depositoryOutExample = new CkmdDepositoryOutExample();
        depositoryOutExample.createCriteria().andIdEqualTo(id);
        result = depositoryOutMapper.updateByExampleSelective(upDepositoryOut, depositoryOutExample);
        //进行中且 更新完成
        if (selectDepositoryOut.getStatus() == 1 && result > 0) {
            //先查询采购退货是否有采购编号
            List<JxmdPurchaseExit> purchaseExitNumber = purchaseExitDao.selectNumber(sourceNumber);
            if (!ObjectUtil.isEmpty(purchaseExitNumber.get(0))) {  //空 true   ; 非空则执行
                JxmdPurchaseExitExample purchaseExitExample = new JxmdPurchaseExitExample();
                purchaseExitExample.createCriteria().andNumberEqualTo(String.valueOf(sourceNumber)); //采购退货编号
                JxmdPurchaseExit purchaseExit = new JxmdPurchaseExit();
                purchaseExit.setStatus(0);
                purchaseExit.setRemark("采购->入库->退购-->出库-->完成出库成功");   //工作流结合
                int i1 = purchaseExitMapper.updateByExampleSelective(purchaseExit, purchaseExitExample);
            }
        }
        return result;
    }

    @Override
    public List<CkmdDepositoryOut> getRowInfoByPurchaseNumber(int number) {
        CkmdDepositoryOutExample depositoryOutExample = new CkmdDepositoryOutExample();
        depositoryOutExample.createCriteria().andSourceNumberEqualTo(Long.valueOf(number));
        return depositoryOutMapper.selectByExample(depositoryOutExample);
    }


}
