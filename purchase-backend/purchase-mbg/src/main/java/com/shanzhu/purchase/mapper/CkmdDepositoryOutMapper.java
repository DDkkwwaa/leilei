package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.CkmdDepositoryOut;
import com.shanzhu.purchase.model.CkmdDepositoryOutExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CkmdDepositoryOutMapper {
    long countByExample(CkmdDepositoryOutExample example);

    int deleteByExample(CkmdDepositoryOutExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CkmdDepositoryOut row);

    int insertSelective(CkmdDepositoryOut row);

    List<CkmdDepositoryOut> selectByExample(CkmdDepositoryOutExample example);

    CkmdDepositoryOut selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CkmdDepositoryOut row, @Param("example") CkmdDepositoryOutExample example);

    int updateByExample(@Param("row") CkmdDepositoryOut row, @Param("example") CkmdDepositoryOutExample example);

    int updateByPrimaryKeySelective(CkmdDepositoryOut row);

    int updateByPrimaryKey(CkmdDepositoryOut row);
}