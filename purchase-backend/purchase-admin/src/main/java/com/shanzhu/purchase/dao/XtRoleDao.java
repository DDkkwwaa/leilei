package com.shanzhu.purchase.dao;

import com.shanzhu.purchase.model.XtmdMenu;
import com.shanzhu.purchase.model.XtmdRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtRoleDao {

    List<XtmdRole> getMenuList(@Param("adminId") Long adminId);

    List<XtmdMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    List<XtmdMenu> getResourceListByRoleId(@Param("roleId") Long roleId);

    List<XtmdRole> queryByUserId(@Param("userId") long userId );

    // roleAndAdmin
    int  roleAndAdmin(@Param("roleId") long roleId);

    int  roleAndMenu(@Param("roleId") Long roleId);

    void delectRelationByRoleId(Long roleId);

    List<Integer> queryroleByAdminId(Long adminId);
}
