package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.JxmdPurchase;
import com.shanzhu.purchase.model.JxmdPurchaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxmdPurchaseMapper {
    long countByExample(JxmdPurchaseExample example);

    int deleteByExample(JxmdPurchaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JxmdPurchase row);

    int insertSelective(JxmdPurchase row);

    List<JxmdPurchase> selectByExample(JxmdPurchaseExample example);

    JxmdPurchase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") JxmdPurchase row, @Param("example") JxmdPurchaseExample example);

    int updateByExample(@Param("row") JxmdPurchase row, @Param("example") JxmdPurchaseExample example);

    int updateByPrimaryKeySelective(JxmdPurchase row);

    int updateByPrimaryKey(JxmdPurchase row);
}