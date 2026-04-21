package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.CkDepositoryDao;
import com.shanzhu.purchase.dao.CkStockDao;
import com.shanzhu.purchase.dao.JxPurchaseExitDao;
import com.shanzhu.purchase.mapper.CkmdDepositoryOutMapper;
import com.shanzhu.purchase.mapper.JxmdPurchaseExitMapper;
import com.shanzhu.purchase.model.CkmdDepositoryOut;
import com.shanzhu.purchase.model.CkmdDepositoryOutExample;
import com.shanzhu.purchase.model.CkmdStock;
import com.shanzhu.purchase.model.JxmdPurchaseExit;
import com.shanzhu.purchase.model.JxmdPurchaseExitExample;
import com.shanzhu.purchase.service.CkDepositoryOutService;
import com.shanzhu.purchase.util.UUidUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CkDepositoryOutServiceImpl implements CkDepositoryOutService {

    @Resource
    private CkmdDepositoryOutMapper depositoryOutMapper;

    @Resource
    private CkStockDao stockDao;

    @Resource
    private CkDepositoryDao depositoryDao;

    @Resource
    private JxmdPurchaseExitMapper purchaseExitMapper;

    @Resource
    private JxPurchaseExitDao purchaseExitDao;

    @Override
    public int create(CkmdDepositoryOut depositoryOut) {
        depositoryOut.setOutId(Long.valueOf(UUidUtils.uuid()));
        depositoryOut.setCreateDate(LocalDateTime.now());
        depositoryOut.setStatus(1);
        BigDecimal shopPrice = depositoryOut.getShopPrice();
        Long shopNumber = depositoryOut.getShopNumber();
        if (shopPrice != null && shopNumber != null) {
            depositoryOut.setTotalPrice(shopPrice.multiply(BigDecimal.valueOf(shopNumber)));
        }
        return depositoryOutMapper.insert(depositoryOut);
    }

    @Override
    public int update(CkmdDepositoryOut depositoryOut) {
        BigDecimal shopPrice = depositoryOut.getShopPrice();
        Long shopNumber = depositoryOut.getShopNumber();
        if (shopPrice != null && shopNumber != null) {
            depositoryOut.setTotalPrice(shopPrice.multiply(BigDecimal.valueOf(shopNumber)));
        }
        return depositoryOutMapper.updateByPrimaryKeySelective(depositoryOut);
    }

    @Override
    public int delete(Long id) {
        return depositoryOutMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CkmdDepositoryOut> list() {
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
        if (depositoryOut.getId() != null && depositoryOut.getId() != 0) {
            return this.update(depositoryOut);
        }
        return this.create(depositoryOut);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int checkById(Long id) {
        int result = 0;
        if (id == null) {
            return result;
        }

        CkmdDepositoryOut selectDepositoryOut = depositoryOutMapper.selectByPrimaryKey(id);
        if (selectDepositoryOut == null) {
            return result;
        }

        String shopName = selectDepositoryOut.getShopName();
        String depositoryName = selectDepositoryOut.getDepository();
        Long shopNumber = selectDepositoryOut.getShopNumber();
        Long sourceNumber = selectDepositoryOut.getSourceNumber();

        List<CkmdStock> stockList = stockDao.getStockByTwoName(shopName, depositoryName);
        if (stockList == null || stockList.isEmpty()) {
            return result;
        }
        CkmdStock stockCheck = stockList.get(0);
        if (stockCheck.getQuantity() < shopNumber) {
            return result;
        }

        CkmdDepositoryOut upDepositoryOut = new CkmdDepositoryOut();
        if (selectDepositoryOut.getStatus() == 1) {
            upDepositoryOut.setDate(LocalDateTime.now());
        }
        upDepositoryOut.setOutInspection(0);
        upDepositoryOut.setStatus(0);

        CkmdDepositoryOutExample depositoryOutExample = new CkmdDepositoryOutExample();
        depositoryOutExample.createCriteria().andIdEqualTo(id);
        result = depositoryOutMapper.updateByExampleSelective(upDepositoryOut, depositoryOutExample);

        if (selectDepositoryOut.getStatus() == 1 && result > 0) {
            int reduceResult = stockDao.reduceQuantityByTwoName(shopNumber, shopName, depositoryName);
            if (reduceResult <= 0) {
                throw new IllegalStateException("reduce stock failed");
            }

            List<JxmdPurchaseExit> purchaseExitNumber = purchaseExitDao.selectNumber(sourceNumber);
            if (!ObjectUtil.isEmpty(purchaseExitNumber) && !purchaseExitNumber.isEmpty()) {
                JxmdPurchaseExitExample purchaseExitExample = new JxmdPurchaseExitExample();
                purchaseExitExample.createCriteria().andNumberEqualTo(String.valueOf(sourceNumber));
                JxmdPurchaseExit purchaseExit = new JxmdPurchaseExit();
                purchaseExit.setStatus(0);
                purchaseExit.setRemark("采购->入库->退货->出库->完成出库成功");
                purchaseExitMapper.updateByExampleSelective(purchaseExit, purchaseExitExample);
            }
        }
        return result;
    }

    @Override
    public List<CkmdDepositoryOut> getRowInfoByPurchaseNumber(Long number) {
        if (number == null) {
            return Collections.emptyList();
        }
        CkmdDepositoryOutExample depositoryOutExample = new CkmdDepositoryOutExample();
        depositoryOutExample.createCriteria().andSourceNumberEqualTo(number);
        return depositoryOutMapper.selectByExample(depositoryOutExample);
    }
}
