package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.JxmdSaleExit;
import com.shanzhu.purchase.model.JxmdSaleExitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxmdSaleExitMapper {
    long countByExample(JxmdSaleExitExample example);

    int deleteByExample(JxmdSaleExitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JxmdSaleExit row);

    int insertSelective(JxmdSaleExit row);

    List<JxmdSaleExit> selectByExample(JxmdSaleExitExample example);

    JxmdSaleExit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") JxmdSaleExit row, @Param("example") JxmdSaleExitExample example);

    int updateByExample(@Param("row") JxmdSaleExit row, @Param("example") JxmdSaleExitExample example);

    int updateByPrimaryKeySelective(JxmdSaleExit row);

    int updateByPrimaryKey(JxmdSaleExit row);
}