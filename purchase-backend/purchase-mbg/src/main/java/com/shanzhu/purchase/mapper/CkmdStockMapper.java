package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.CkmdStock;
import com.shanzhu.purchase.model.CkmdStockExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CkmdStockMapper {
    long countByExample(CkmdStockExample example);

    int deleteByExample(CkmdStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CkmdStock row);

    int insertSelective(CkmdStock row);

    List<CkmdStock> selectByExample(CkmdStockExample example);

    CkmdStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CkmdStock row, @Param("example") CkmdStockExample example);

    int updateByExample(@Param("row") CkmdStock row, @Param("example") CkmdStockExample example);

    int updateByPrimaryKeySelective(CkmdStock row);

    int updateByPrimaryKey(CkmdStock row);
}