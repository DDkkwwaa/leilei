package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.JcmdShopType;
import com.shanzhu.purchase.model.JcmdShopTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JcmdShopTypeMapper {
    long countByExample(JcmdShopTypeExample example);

    int deleteByExample(JcmdShopTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JcmdShopType row);

    int insertSelective(JcmdShopType row);

    List<JcmdShopType> selectByExample(JcmdShopTypeExample example);

    JcmdShopType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") JcmdShopType row, @Param("example") JcmdShopTypeExample example);

    int updateByExample(@Param("row") JcmdShopType row, @Param("example") JcmdShopTypeExample example);

    int updateByPrimaryKeySelective(JcmdShopType row);

    int updateByPrimaryKey(JcmdShopType row);
}