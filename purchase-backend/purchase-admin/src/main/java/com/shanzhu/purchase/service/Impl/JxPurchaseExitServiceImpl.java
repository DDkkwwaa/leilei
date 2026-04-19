package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.JxPurchaseExitDao;
import com.shanzhu.purchase.mapper.JxmdPurchaseExitMapper;
import com.shanzhu.purchase.model.JxmdPurchaseExit;
import com.shanzhu.purchase.model.JxmdPurchaseExitExample;
import com.shanzhu.purchase.service.JxPurchaseExitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门  service
 */
@Service
public class JxPurchaseExitServiceImpl implements JxPurchaseExitService {

    @Resource
    private JxmdPurchaseExitMapper purchaseExitMapper;

    @Override
    public int create(JxmdPurchaseExit purchaseExit) {
        purchaseExit.setTime(LocalDateTime.now());
        Long  TotalPrice =120l;  //不同属性的乘积问题
        purchaseExit.setTotalPrice(BigDecimal.valueOf(TotalPrice));
        purchaseExit.setStatus(1); //这个设置int  修改varchar
        //设置编号
        purchaseExit.setExitNumber("123456"); //默认后期修改
        return purchaseExitMapper.insert(purchaseExit);
    }

    @Override
    public int update( JxmdPurchaseExit purchaseExit) {
        return purchaseExitMapper.updateByPrimaryKeySelective(purchaseExit);
    }

    @Override
    public int delete(Long id) {
        return purchaseExitMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<JxmdPurchaseExit> list() {
        return purchaseExitMapper.selectByExample(new JxmdPurchaseExitExample());
    }

    @Override
    public List<JxmdPurchaseExit> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        JxmdPurchaseExitExample example = new JxmdPurchaseExitExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andNumberLike("%" + keyword + "%");
        }
        return purchaseExitMapper.selectByExample(example);
    }

    @Override
    public int addOrUpdate(JxmdPurchaseExit purchaseExit) {
        // 判断 编号是否存在，如果存在就走更新的逻辑否则新增数据
        int res =0;
        if(purchaseExit.getId() != null && purchaseExit.getId() != 0){
            // 表示更新操作
            res =this.update(purchaseExit);
        }else{
            res =  this.create(purchaseExit);
        }
        return res;
    }

}
