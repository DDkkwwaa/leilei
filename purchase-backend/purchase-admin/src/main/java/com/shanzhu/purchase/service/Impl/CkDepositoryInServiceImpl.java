package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.CkDepositoryInDao;
import com.shanzhu.purchase.mapper.CkmdDepositoryInMapper;
import com.shanzhu.purchase.model.CkmdDepositoryIn;
import com.shanzhu.purchase.model.CkmdDepositoryInExample;
import com.shanzhu.purchase.service.CkDepositoryInService;
import com.shanzhu.purchase.util.UUidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库清单
 */
@Service
public class CkDepositoryInServiceImpl implements CkDepositoryInService {

    @Resource
    private CkmdDepositoryInMapper depositoryInMapper;

    @Override
    public int create(CkmdDepositoryIn DepositoryIn) {
        Integer uuid = UUidUtils.uuid();
        DepositoryIn.setInId(Long.valueOf(uuid));
        DepositoryIn.setStatus(1); //默认未入库
        DepositoryIn.setCreateDate(LocalDateTime.now());
        BigDecimal shopPrice = DepositoryIn.getShopPrice();
        Long shopNumber = DepositoryIn.getShopNumber();
        if (shopPrice != null && shopNumber != null) {
            BigDecimal bigDecimal = new BigDecimal(shopNumber);
            BigDecimal priceTotal = bigDecimal.multiply(shopPrice);
            DepositoryIn.setPriceTotal(priceTotal);
        }
        return depositoryInMapper.insertSelective(DepositoryIn);
    }

    @Override
    public int update(CkmdDepositoryIn CkmdDepositoryIn) {
        return depositoryInMapper.updateByPrimaryKeySelective(CkmdDepositoryIn);
    }

    @Override
    public int delete(Long id) {
        return depositoryInMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List list() {
        return depositoryInMapper.selectByExample(new CkmdDepositoryInExample());
    }

    @Override
    public List<CkmdDepositoryIn> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CkmdDepositoryInExample example = new CkmdDepositoryInExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andShopNameLike("%" + keyword + "%");
        }
        return depositoryInMapper.selectByExample(example);
    }

    @Override
    public boolean checkDepositoryInId(Long depositoryInId) {
        CkmdDepositoryInExample depositoryInExample = new CkmdDepositoryInExample();
        CkmdDepositoryInExample.Criteria criteria = depositoryInExample.createCriteria();
        criteria.andShopNumberEqualTo(depositoryInId);
        return false;
    }

    @Override
    public int addOrUpdateDepositoryIn(CkmdDepositoryIn depositoryIn) {
        // 判断 编号是否存在，如果存在就走更新的逻辑否则新增数据
        int res = 0;
        if (depositoryIn.getId() != null && depositoryIn.getId() != 0) {
            // 表示更新操作
            res = this.update(depositoryIn);
        } else {
            res = this.create(depositoryIn);
        }
        return res;
    }

    @Override
    public int checkById(Long id) {
        //校验id
        if (id == null) return 0;
        //更新的内容
        CkmdDepositoryIn UpdateDepositoryIn = new CkmdDepositoryIn();
        UpdateDepositoryIn.setIsInspection(0); //质检 0为已质检
        CkmdDepositoryIn selectDepositoryIn = depositoryInMapper.selectByPrimaryKey(id);  //通过id获取数据
        //入库商品 -入库仓库  |   入库仓库 - 入库的数量
        /**
         * 1.库存列表   条件：商品、仓库  修改：增加库存数量
         * 2.仓库列表   条件：商品、仓库  修改： 减少剩余量
         */
        String shopName = selectDepositoryIn.getShopName();
        String depositoryName = selectDepositoryIn.getDepository();
        Long shopNumber = selectDepositoryIn.getShopNumber();  //入库数量

        if (selectDepositoryIn.getIsInspection() == 1) {  //   判断是否质检状态  1未质检  0 质检
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            String userName = token.getPrincipal().toString();
            UpdateDepositoryIn.setInUser(userName);   //设置入库人
            UpdateDepositoryIn.setDate(LocalDateTime.now());  //设置入库日期
            UpdateDepositoryIn.setStatus(0); //设置 入库0
        }
        // 更新的条件
        CkmdDepositoryInExample depositoryInExample = new CkmdDepositoryInExample();
        depositoryInExample.createCriteria().andIdEqualTo(id);

        return depositoryInMapper.updateByExampleSelective(UpdateDepositoryIn, depositoryInExample);
    }

    /**
     * 关联采购表    采购编号
     * 通过 采购编号 获取入库清单
     */
    @Override
    public List<CkmdDepositoryIn> getRowInfoByPurchaseNumber(int number) {
        CkmdDepositoryInExample depositoryInExample = new CkmdDepositoryInExample();
        depositoryInExample.createCriteria().andSourceNumberEqualTo(number);
        List<CkmdDepositoryIn> ckmdDepositoryIns = depositoryInMapper.selectByExample(depositoryInExample);
        return ckmdDepositoryIns;
    }
}
