package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.JxSaleExitDao;
import com.shanzhu.purchase.mapper.JxmdSaleExitMapper;
import com.shanzhu.purchase.model.JxmdSaleExit;
import com.shanzhu.purchase.model.JxmdSaleExitExample;
import com.shanzhu.purchase.service.JxSaleExitService;
import com.shanzhu.purchase.util.UUidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门  service
 */
@Service
public class JxSaleExitServiceImpl implements JxSaleExitService {

    @Autowired
    private JxmdSaleExitMapper saleExitMapper;

    @Autowired
    private JxSaleExitDao saleExitDao;


    @Override
    public int create(JxmdSaleExit saleExit) {
        saleExit.setStatus(1); //默认进行中
        saleExit.setTime(LocalDateTime.now());
        saleExit.setExitNumber(String.valueOf(UUidUtils.uuid()));  //销售退货编号
        BigDecimal  TotalPrice =null;  //不同属性的乘积问题
        BigDecimal price = saleExit.getPrice();
        Long num = saleExit.getNum();
//        问题：  null  与  " "
        if (price !=null && num !=null){
            BigDecimal bigDecimal = new BigDecimal(num);
            TotalPrice = bigDecimal.multiply(price);
            saleExit.setTotalPrice(TotalPrice);
        }
        return saleExitMapper.insertSelective(saleExit);
    }

    @Override
    public int update( JxmdSaleExit saleExit) {
            //计算总价  直接重新计算一下
        Long num = saleExit.getNum(); //获取数量
        BigDecimal price = saleExit.getPrice();  //单价

        if (price !=null && num !=null) {
            BigDecimal numDivide = new BigDecimal(num);
            BigDecimal divideTotalPrice = numDivide.divide(price, 2);  //计算总价保留两位数
            saleExit.setTotalPrice(divideTotalPrice);
        }
        //updateByPrimaryKeySelective 传入字段为null则不更新
        return saleExitMapper.updateByPrimaryKeySelective(saleExit);
    }

    @Override
    public int delete(Long id) {
        return saleExitMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<JxmdSaleExit> list() {
        return saleExitMapper.selectByExample(new JxmdSaleExitExample());
    }

    @Override
    public List<JxmdSaleExit> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        JxmdSaleExitExample example = new JxmdSaleExitExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andNumberLike("%" + keyword + "%");
        }
        return saleExitMapper.selectByExample(example);
    }

    @Override
    public int addOrUpdate(JxmdSaleExit saleExit) {
        // 判断 编号是否存在，如果存在就走更新的逻辑否则新增数据
        int res =0;
        if(saleExit.getId() != null && saleExit.getId() != 0){
            // 表示更新操作
            res =this.update(saleExit);
        }else{
            res =  this.create(saleExit);
        }
        return res;
    }

}
