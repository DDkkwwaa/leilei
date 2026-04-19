package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.JcmdSupplier;
import com.shanzhu.purchase.model.JcmdSupplierExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JcmdSupplierMapper {
    long countByExample(JcmdSupplierExample example);

    int deleteByExample(JcmdSupplierExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JcmdSupplier row);

    int insertSelective(JcmdSupplier row);

    List<JcmdSupplier> selectByExample(JcmdSupplierExample example);

    JcmdSupplier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") JcmdSupplier row, @Param("example") JcmdSupplierExample example);

    int updateByExample(@Param("row") JcmdSupplier row, @Param("example") JcmdSupplierExample example);

    int updateByPrimaryKeySelective(JcmdSupplier row);

    int updateByPrimaryKey(JcmdSupplier row);
}