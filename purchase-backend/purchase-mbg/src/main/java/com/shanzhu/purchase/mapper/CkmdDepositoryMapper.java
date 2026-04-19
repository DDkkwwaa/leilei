package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.CkmdDepository;
import com.shanzhu.purchase.model.CkmdDepositoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CkmdDepositoryMapper {
    long countByExample(CkmdDepositoryExample example);

    int deleteByExample(CkmdDepositoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CkmdDepository row);

    int insertSelective(CkmdDepository row);

    List<CkmdDepository> selectByExample(CkmdDepositoryExample example);

    CkmdDepository selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CkmdDepository row, @Param("example") CkmdDepositoryExample example);

    int updateByExample(@Param("row") CkmdDepository row, @Param("example") CkmdDepositoryExample example);

    int updateByPrimaryKeySelective(CkmdDepository row);

    int updateByPrimaryKey(CkmdDepository row);
}