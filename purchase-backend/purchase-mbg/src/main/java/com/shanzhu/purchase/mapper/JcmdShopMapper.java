package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.JcmdShop;
import com.shanzhu.purchase.model.JcmdShopExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JcmdShopMapper {
    long countByExample(JcmdShopExample example);

    int deleteByExample(JcmdShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JcmdShop row);

    int insertSelective(JcmdShop row);

    List<JcmdShop> selectByExample(JcmdShopExample example);

    JcmdShop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") JcmdShop row, @Param("example") JcmdShopExample example);

    int updateByExample(@Param("row") JcmdShop row, @Param("example") JcmdShopExample example);

    int updateByPrimaryKeySelective(JcmdShop row);

    int updateByPrimaryKey(JcmdShop row);
}