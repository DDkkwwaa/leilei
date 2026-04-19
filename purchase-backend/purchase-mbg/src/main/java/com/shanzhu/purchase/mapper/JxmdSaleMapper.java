package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.JxmdSale;
import com.shanzhu.purchase.model.JxmdSaleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxmdSaleMapper {
    long countByExample(JxmdSaleExample example);

    int deleteByExample(JxmdSaleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JxmdSale row);

    int insertSelective(JxmdSale row);

    List<JxmdSale> selectByExample(JxmdSaleExample example);

    JxmdSale selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") JxmdSale row, @Param("example") JxmdSaleExample example);

    int updateByExample(@Param("row") JxmdSale row, @Param("example") JxmdSaleExample example);

    int updateByPrimaryKeySelective(JxmdSale row);

    int updateByPrimaryKey(JxmdSale row);
}