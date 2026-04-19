package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.JxPurchaseDao;
import com.shanzhu.purchase.dao.JxPurchaseExitDao;
import com.shanzhu.purchase.mapper.*;
import com.shanzhu.purchase.model.*;
import com.shanzhu.purchase.service.CkDepositoryInService;
import com.shanzhu.purchase.service.JxPurchaseService;
import com.shanzhu.purchase.util.UUidUtils;
import com.shanzhu.purchase.util.calculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门  service
 */
@Service
public class JxPurchaseServiceImpl implements JxPurchaseService {

    @Resource
    private JxmdPurchaseMapper purchaseMapper;

    @Resource
    private JxPurchaseDao purchaseDao;

    @Resource
    private CkmdDepositoryInMapper depositoryInMapper;

    @Resource
    private CkmdDepositoryMapper depositoryMapper;

    @Resource
    private CkDepositoryInService depositoryInService;

    @Resource
    private JxmdPurchaseExitMapper purchaseExitMapper;
    @Resource
    private JxPurchaseExitDao purchaseExitDao;

    @Resource
    private CkmdDepositoryOutMapper depositoryOutMapper;  //出库清单

    @Override
    public int create(JxmdPurchase purchase) {
        purchase.setNumber(UUidUtils.uuid().toString());  //采购编号
        purchase.setTime(LocalDateTime.now());
        Long TotalPrice = 120l;  //不同属性的乘积问题
        purchase.setTotalPrice(BigDecimal.valueOf(TotalPrice));
        purchase.setIsDestroy(0);
        if (purchase.getStatus() == null) {
            purchase.setStatus(1); //状态默认进行中
        }
        return purchaseMapper.insert(purchase);
    }

    @Override
    public int update(JxmdPurchase purchase) {
        return purchaseMapper.updateByPrimaryKeySelective(purchase);
    }

    @Override
    public int delete(Long id) {
        return purchaseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<JxmdPurchase> list() {
        return purchaseMapper.selectByExample(new JxmdPurchaseExample());
    }

    @Override
    public List<JxmdPurchase> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        JxmdPurchaseExample example = new JxmdPurchaseExample();
        JxmdPurchaseExample.Criteria c = example.createCriteria();
        c.andIsDestroyEqualTo(0);  //0订单有效 未退
        if (!StrUtil.isEmpty(keyword)) {
            c.andPurchaseUserLike("%" + keyword + "%");
        }
        return purchaseMapper.selectByExample(example);
    }

    @Override
    public int addOrUpdate(JxmdPurchase purchase) {
        // 判断 编号是否存在，如果存在就走更新的逻辑否则新增数据
        int res = 0;
        if (purchase.getId() != null && purchase.getId() != 0) {
            // 表示更新操作
            res = this.update(purchase);
        } else {
            res = this.create(purchase);
        }
        return res;
    }

    /**
     * 采购完成后就可以入库了
     */
    @Override
    public int putStock(String depositoryName, JxmdPurchase purchase) {

        int result = 0;
        //  后端也校验 是否有仓库名
        if (depositoryName == null || purchase.getNumber() == null) {
            return result;
        }

        /**
         * 不能出现重复入库的情况
         */
        Long number = Long.valueOf(purchase.getNumber());
        CkmdDepositoryInExample depositoryInExample = new CkmdDepositoryInExample();
        depositoryInExample.createCriteria().andInIdEqualTo(number);
        List<CkmdDepositoryIn> ckmdDepositoryIns = depositoryInMapper.selectByExample(depositoryInExample);
        if (!CollUtil.isEmpty(ckmdDepositoryIns)) {   //空为 true    如果已经存在则直接返回
            return 3;
        }

        /**
         *  仓库名 去查找对应的仓库剩余容量 如果容量充足则
         *  执行1 否 返回 容量不足
         *  执行2  存 及其创建
         */
        CkmdDepositoryExample depositoryExample = new CkmdDepositoryExample();
        depositoryExample.createCriteria().andNameEqualTo(depositoryName);
        List<CkmdDepository> Depository = depositoryMapper.selectByExample(depositoryExample);
        CkmdDepository depository = Depository.get(0);
        Long surplus = depository.getSurplus();   //获取仓库剩余容量
        BigDecimal surplusBigDecimal = calculationUtils.INTCalculatingVolumeToWeight(surplus);  //仓库容量转质量
        BigDecimal quantity = BigDecimal.valueOf(purchase.getQuantity()); //获取采购数量
        if (surplusBigDecimal.compareTo(quantity) == -1) {   //仓库剩余容量小于采购量
            return 2;
        }

        /**
         * 需要：
         * 入库编号 入库仓库名、 入库商品、 入库商品单价、 商品数量
         * 总价、 规格、 日期、入库人、 供应商名， 质检、 是否入库
         */
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = token.getPrincipal().toString();
        if (purchase != null) {
            CkmdDepositoryIn depositoryIn = new CkmdDepositoryIn();
            depositoryIn.setInId(Long.valueOf(purchase.getNumber()));  //采购id就是入库id
            depositoryIn.setDepository(depositoryName);   //入库仓库
            depositoryIn.setShopName(purchase.getShop());  //入库商品
            depositoryIn.setShopPrice(purchase.getPrice()); //入库价格
            depositoryIn.setShopSupplier(purchase.getSupplier());  //供应商
            depositoryIn.setShopNumber(purchase.getQuantity());   //商品数量
            depositoryIn.setPriceTotal(purchase.getTotalPrice());  //商品总价
            depositoryIn.setSpecs(purchase.getSpecs());   //单位规格 斤 个 盒
            depositoryIn.setDate(LocalDateTime.now());  //入库日期
            depositoryIn.setInUser(userName); //入库人
            depositoryIn.setIsInspection(0);  //是否质检  是
            depositoryIn.setStatus(0);         //是否入库  是
            result = depositoryInMapper.insertSelective(depositoryIn);
        }
        return result;
    }


    @Override
    public List getNumberAndSupplierName() {
        List<Map<String, String>> list = new ArrayList<>();
        List<JxmdPurchase> supplierList = purchaseMapper.selectByExample(new JxmdPurchaseExample());
        if (supplierList != null) {
            for (JxmdPurchase purchase : supplierList) {
                String supplier = purchase.getSupplier(); //供应商
                String number = purchase.getNumber(); //编号
                Map<String, String> map = new HashMap<>();
                map.put("value", number);
                map.put("address", supplier);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 退货操作
     * <p>
     * status  0 完成 ;  1 进行中
     * 如果item.status  为0完成 则删除采购信息，生成采购退货信息  生成出库清单
     * 如果为1进行中 则删除采购信息，生成采购退货信息，不生产出库清单
     * 删除采购信息   生成 采购退货
     * 0 生成出货清单
     *
     * @return
     */
    @Override
    public int checkAndExitGoods(JxmdPurchase purchase) {
        Long id = purchase.getId(); //退货商品主键
        Integer status = purchase.getStatus();
        String checkNumber = purchase.getNumber(); //采购编号
        int flag = 0;
        /**
         * 防止重复生成退货 查看采购退货单是否已经存在
         */
        List<JxmdPurchaseExit> byField = purchaseExitDao.getByField(checkNumber);
        if (byField != null && byField.size() != 0) {  //说明存在 已退货
            return 0;  //
        }

        if (purchase != null) {
            JxmdPurchaseExit purchaseExit = new JxmdPurchaseExit();
            purchaseExit.setExitNumber(String.valueOf(UUidUtils.uuid())); //设置退采编号uuid
            purchaseExit.setNumber(purchase.getNumber());  //采购编号
            purchaseExit.setNum(Math.toIntExact(purchase.getQuantity()));   //采购数量
            purchaseExit.setPrice(purchase.getPrice());//采购价格
            purchaseExit.setTotalPrice(purchase.getTotalPrice()); //采购总价
            purchaseExit.setTime(LocalDateTime.now()); //退采日期
            purchaseExit.setReason(purchase.getRemark()); //退采原因
            purchaseExit.setSpecs(purchase.getSpecs());
            if (status == 1) { // 进行中则表示未入库 可以直接设置退货
                purchaseExit.setStatus(0);
            } else {
                purchaseExit.setStatus(1);
            }
            int insert = purchaseExitMapper.insertSelective(purchaseExit);
            flag += insert;
            /**
             * 完成状态 即入库
             *  采购 编号------->入库清单  中含  采购编号
             *
             * 生成 出库清单
             *
             *   0完成状态  表示已经入库
             *   通过采购编号 查找对应的入库清单，并判断是否已经质检
             *   已质检： 需要 减少库存
             */
            if (status == 0) {
                int number = Integer.parseInt(purchase.getNumber()); //采购编号
                //查找对应的入库清单
                List<CkmdDepositoryIn> depositoryIn = depositoryInService.getRowInfoByPurchaseNumber(number);
                //获取源编号(采购编号)、以及仓库
                CkmdDepositoryIn depositoryIn1 = depositoryIn.get(0);
                Integer sourceNumber = depositoryIn1.getSourceNumber(); //获取源编号
                String depository = depositoryIn1.getDepository();      //获取仓库名
                /**
                 * 生成出库清单
                 */
                CkmdDepositoryOut depositoryOut = new CkmdDepositoryOut();
                depositoryOut.setOutId(Long.valueOf(UUidUtils.uuid()));  //设置出库uuid
                depositoryOut.setSourceNumber(Long.valueOf(sourceNumber)); //设置源编号
                depositoryOut.setDepository(depository);  //设置仓库
                depositoryOut.setShopName(purchase.getShop());  //设置商品
                depositoryOut.setShopPrice(purchase.getPrice()); //设置价格
                depositoryOut.setTotalPrice(purchase.getTotalPrice()); //设置总价
                depositoryOut.setSpecs(purchase.getSpecs());   //设置规格
                depositoryOut.setCreateDate(LocalDateTime.now());  //设置创建时间
                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                depositoryOut.setOutUser(token.getPrincipal().toString());// 设置出库人
                depositoryOut.setShopSupplier(purchase.getSupplier());  //设置供应商
                depositoryOut.setStatus(1); // 审核后才能出库
                depositoryOut.setOutInspection(1);  //默认需要审核
                int i1 = depositoryOutMapper.insertSelective(depositoryOut);  //生成
                flag += i1;
            }
        }

        return flag;
    }
}
