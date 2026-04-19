package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.QjmdTableList;
import com.shanzhu.purchase.model.QjmdTableListExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QjmdTableListMapper {
    long countByExample(QjmdTableListExample example);

    int deleteByExample(QjmdTableListExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QjmdTableList row);

    int insertSelective(QjmdTableList row);

    List<QjmdTableList> selectByExample(QjmdTableListExample example);

    QjmdTableList selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") QjmdTableList row, @Param("example") QjmdTableListExample example);

    int updateByExample(@Param("row") QjmdTableList row, @Param("example") QjmdTableListExample example);

    int updateByPrimaryKeySelective(QjmdTableList row);

    int updateByPrimaryKey(QjmdTableList row);
}