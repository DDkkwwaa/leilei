package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.CkmdDepositoryIn;
import com.shanzhu.purchase.model.CkmdDepositoryInExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CkmdDepositoryInMapper {
    long countByExample(CkmdDepositoryInExample example);

    int deleteByExample(CkmdDepositoryInExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CkmdDepositoryIn row);

    int insertSelective(CkmdDepositoryIn row);

    List<CkmdDepositoryIn> selectByExample(CkmdDepositoryInExample example);

    CkmdDepositoryIn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CkmdDepositoryIn row, @Param("example") CkmdDepositoryInExample example);

    int updateByExample(@Param("row") CkmdDepositoryIn row, @Param("example") CkmdDepositoryInExample example);

    int updateByPrimaryKeySelective(CkmdDepositoryIn row);

    int updateByPrimaryKey(CkmdDepositoryIn row);
}