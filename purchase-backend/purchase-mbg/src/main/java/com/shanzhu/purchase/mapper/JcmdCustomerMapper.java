package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.JcmdCustomer;
import com.shanzhu.purchase.model.JcmdCustomerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JcmdCustomerMapper {
    long countByExample(JcmdCustomerExample example);

    int deleteByExample(JcmdCustomerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JcmdCustomer row);

    int insertSelective(JcmdCustomer row);

    List<JcmdCustomer> selectByExample(JcmdCustomerExample example);

    JcmdCustomer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") JcmdCustomer row, @Param("example") JcmdCustomerExample example);

    int updateByExample(@Param("row") JcmdCustomer row, @Param("example") JcmdCustomerExample example);

    int updateByPrimaryKeySelective(JcmdCustomer row);

    int updateByPrimaryKey(JcmdCustomer row);
}