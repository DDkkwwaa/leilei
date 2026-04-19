package com.shanzhu.purchase.dao;


import com.shanzhu.purchase.model.XtmdMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtMenuDao {

    List<XtmdMenu> ListRoleMenuById(@Param("RoleId") Long RoleId); //查询所有的父菜单

    List<XtmdMenu> ListMenuParent();

    List<XtmdMenu> childMenu(@Param("id") Long id);

    List<Integer> queryMenuByRoleId(@Param("RoleId") Long RoleId);

    List<XtmdMenu> selectShowMenuByAdminName( @Param("userName") String userName);

    List<XtmdMenu> selectChildMenuByAdminName(String userName, Long id);
}
