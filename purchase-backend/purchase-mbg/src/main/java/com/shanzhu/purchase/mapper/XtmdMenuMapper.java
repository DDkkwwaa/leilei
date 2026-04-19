package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdMenu;
import com.shanzhu.purchase.model.XtmdMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdMenuMapper {
    long countByExample(XtmdMenuExample example);

    int deleteByExample(XtmdMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(XtmdMenu row);

    int insertSelective(XtmdMenu row);

    List<XtmdMenu> selectByExample(XtmdMenuExample example);

    XtmdMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") XtmdMenu row, @Param("example") XtmdMenuExample example);

    int updateByExample(@Param("row") XtmdMenu row, @Param("example") XtmdMenuExample example);

    int updateByPrimaryKeySelective(XtmdMenu row);

    int updateByPrimaryKey(XtmdMenu row);
}