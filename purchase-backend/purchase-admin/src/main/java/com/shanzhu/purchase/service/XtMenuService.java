package com.shanzhu.purchase.service;


import com.shanzhu.purchase.dto.ShowMenu;
import com.shanzhu.purchase.model.XtmdMenu;

import java.util.List;

/**
 * 菜单 service   前端暂时没有菜单功能
 */
public interface XtMenuService {

    /**
     * 添加菜单
     */
    int create(XtmdMenu menu);

    /**
     * 修改菜单信息
     */
    int update(Long id, XtmdMenu menu);

    /**
     * 批量删除菜单
     */
    int delete(List<Long> ids);

    /**
     * 获取所有菜单列表
     */
    List<XtmdMenu> list();

    /**
     * 分页获取角色列表
     */
    List<XtmdMenu> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 查询父菜单信息
     *
     * @param
     * @return
     */
    List<XtmdMenu> ListMenuParent();

    /**
     * 查询子菜单信息
     *
     * @param id
     * @return
     */
    List<XtmdMenu> childMenu(Long id);

    List<Integer> queryMenuByRoleId(Long roleId);

    List<ShowMenu> selectShowMenuByAdminName();
}
