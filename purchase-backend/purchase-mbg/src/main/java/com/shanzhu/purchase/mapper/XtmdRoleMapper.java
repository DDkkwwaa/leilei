package com.shanzhu.purchase.mapper;

import com.shanzhu.purchase.model.XtmdRole;
import com.shanzhu.purchase.model.XtmdRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtmdRoleMapper {
    long countByExample(XtmdRoleExample example);

    int deleteByExample(XtmdRoleExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(XtmdRole row);

    int insertSelective(XtmdRole row);

    List<XtmdRole> selectByExample(XtmdRoleExample example);

    XtmdRole selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("row") XtmdRole row, @Param("example") XtmdRoleExample example);

    int updateByExample(@Param("row") XtmdRole row, @Param("example") XtmdRoleExample example);

    int updateByPrimaryKeySelective(XtmdRole row);

    int updateByPrimaryKey(XtmdRole row);
}