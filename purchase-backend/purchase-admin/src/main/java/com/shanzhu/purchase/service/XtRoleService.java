package com.shanzhu.purchase.service;

import com.shanzhu.purchase.dto.RoleMenuRelationDto;
import com.shanzhu.purchase.model.XtmdMenu;
import com.shanzhu.purchase.model.XtmdRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 后台角色管理Service
 */
public interface XtRoleService {

    /**
     * 通过id获取
     *
     * @param userId
     * @return
     */
    List<XtmdRole> queryByUserId(Long userId);

    /**
     * 添加角色
     */
    int create(XtmdRole role);

    /**
     * 修改角色信息
     */
    int update(XtmdRole role);

    /**
     * 批量删除角色
     */
    boolean delete(Long id);

    /**
     * 获取所有角色列表
     */
    List<XtmdRole> list();

    /**
     * 分页获取角色列表
     */
    List<XtmdRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<XtmdRole> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
    List<XtmdMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源------------------暂时用menu
     */
    List<XtmdMenu> listResource(Long roleId);


    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);

    /**
     * 角色是后存在
     *
     * @param roleName
     * @return
     */
    boolean checkRoleName(String roleName);

    int addOrUpdateRole(XtmdRole role);


    Map<String, Object> getRoleMenuById(Long roleId);

    /**
     * 给角色分配菜单
     */

    int roleToAuth(RoleMenuRelationDto roleMenu);


    List<Integer> getRoleByAdminId(Long adminId);

}
