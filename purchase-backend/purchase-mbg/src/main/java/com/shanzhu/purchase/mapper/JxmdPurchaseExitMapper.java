package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.JxmdPurchaseExit;
import com.shanzhu.purchase.model.JxmdPurchaseExitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxmdPurchaseExitMapper {
    long countByExample(JxmdPurchaseExitExample example);

    int deleteByExample(JxmdPurchaseExitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JxmdPurchaseExit row);

    int insertSelective(JxmdPurchaseExit row);

    List<JxmdPurchaseExit> selectByExample(JxmdPurchaseExitExample example);

    JxmdPurchaseExit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") JxmdPurchaseExit row, @Param("example") JxmdPurchaseExitExample example);

    int updateByExample(@Param("row") JxmdPurchaseExit row, @Param("example") JxmdPurchaseExitExample example);

    int updateByPrimaryKeySelective(JxmdPurchaseExit row);

    int updateByPrimaryKey(JxmdPurchaseExit row);
}