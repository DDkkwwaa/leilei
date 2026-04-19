package com.shanzhu.purchase.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.XtRoleDao;
import com.shanzhu.purchase.dto.RoleMenuRelationDto;
import com.shanzhu.purchase.mapper.XtmdRoleMapper;
import com.shanzhu.purchase.mapper.XtmdRoleMenuRelationMapper;
import com.shanzhu.purchase.model.*;
import com.shanzhu.purchase.service.XtMenuService;
import com.shanzhu.purchase.service.XtRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * role service层
 */
@Service
public class XtRoleServiceImpl implements XtRoleService {


    @Resource
    private XtRoleDao roleDao;

    @Resource
    private XtmdRoleMapper roleMapper;

    @Resource
    private XtmdRoleMenuRelationMapper roleMenuRelationMapper;

    @Resource
    private XtMenuService menuService;

    @Override
    public List<XtmdRole> queryByUserId(Long userId) {
        return roleDao.queryByUserId(userId);
    }

    @Override
    public int create(XtmdRole role) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = token.getPrincipal().toString();
        role.setBuildUser(userName);
        role.setCreateTime(LocalDateTime.now());

        return roleMapper.insert(role);
    }

    /**
     * 更新角色
     */
    public int update(XtmdRole role) {
        role.setUpdateTime(LocalDateTime.now());
        return roleMapper.updateByPrimaryKeySelective(role);
    }


    @Override
    public boolean delete(Long roleId) {
        //删除角色信息   删除角色与菜单信息
        //  用户--角色   角色--菜单
        //后期判读 status是否正常
        int count = roleDao.roleAndAdmin(roleId);
        if (count > 0) { //存在用户正在使用的角色则不能删除
            return false;
        } else {
            //删除 角色的同时删除和菜单的关系
            roleDao.delectRelationByRoleId(roleId);
            roleMapper.deleteByPrimaryKey(roleId);
            return true;
        }
    }

    @Override
    public List<XtmdRole> list() {
        return roleMapper.selectByExample(new XtmdRoleExample());
    }

    @Override
    public List<XtmdRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XtmdRoleExample example = new XtmdRoleExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andRoleNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<XtmdRole> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<XtmdMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<XtmdMenu> listResource(Long roleId) {
        return roleDao.getResourceListByRoleId(roleId);
    }


    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        return 0;
    }

    @Override
    public boolean checkRoleName(String roleName) {
        //criteria where条件为 角色名为roleName
        XtmdRoleExample roleExample = new XtmdRoleExample();
        XtmdRoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andRoleNameEqualTo(roleName);
        //查询 条件 的记录数
        long count = roleMapper.countByExample(roleExample);
        return count > 0;   //大于0表示存在 为true
    }

    /**
     * 添加 或更新角色
     */
    @Override
    public int addOrUpdateRole(XtmdRole role) {
        // 判断 角色编号是否存在，如果存在就走更新的逻辑否则新增数据
        int res;
        if (role.getRoleId() != null && role.getRoleId() != 0) {
            res = this.update(role);  // 表示更新操作
        } else {
            res = this.create(role);
        }

        return res;
    }

    /**
     * 查询角色分配的菜单信息
     */
    @Override
    public Map<String, Object> getRoleMenuById(Long roleId) {
        List<Map<String, Object>> treeList = new ArrayList<>();
        /**
         * 1.查询所有的菜单信息
         *  1.查询父菜单信息
         */
        List<XtmdMenu> parents = menuService.ListMenuParent();  //查询出一级菜单信息
        if (parents != null && parents.size() > 0) {
            for (XtmdMenu parent : parents) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", parent.getId());
                map.put("label", parent.getTitle());

                //根据父id查询子菜单
                Long id = parent.getId();
                List<Map<String, Object>> childList = new ArrayList<>();
                List<XtmdMenu> childMenus = menuService.childMenu(id);
                if (childMenus != null && childMenus.size() > 0) {
                    for (XtmdMenu childMenu : childMenus) {
                        Map<String, Object> childmap = new HashMap<String, Object>();
                        childmap.put("id", childMenu.getId());
                        childmap.put("label", childMenu.getTitle());
                        childList.add(childmap);
                    }
                }
                //父子菜单关联
                map.put("children", childList);
                treeList.add(map);
            }
        }

        //根据角色编号查询分配的菜单编号 checkKey 前端的
        List<Integer> menuIds = menuService.queryMenuByRoleId(roleId);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("checks", menuIds);
        resMap.put("treeData", treeList);
        return resMap;
    }

    @Override
    public int roleToAuth(RoleMenuRelationDto roleMenu) {
        //先删除原有关系 角色-菜单关系表
        if (roleMenu.getRoleId() != null) {
            XtmdRoleMenuRelationExample example = new XtmdRoleMenuRelationExample();
            example.createCriteria().andRoleIdEqualTo(roleMenu.getRoleId());
            roleMenuRelationMapper.deleteByExample(example);
        }
        List<Long> menuIds = roleMenu.getMenuIds();
        if (menuIds != null && menuIds.size() > 0) {
            //批量插入新关系
            for (Long menuId : menuIds) {
                XtmdRoleMenuRelation relation = new XtmdRoleMenuRelation();
                relation.setRoleId(roleMenu.getRoleId());
                relation.setMenuId(menuId);
                roleMenuRelationMapper.insert(relation);
            }
        }
        return menuIds.size();
    }

    /**
     * 通过用户id获取所拥有的角色id
     */
    @Override
    public List<Integer> getRoleByAdminId(Long adminId) {
        List<Integer> roleIdList = roleDao.queryroleByAdminId(adminId);
        return roleIdList;
    }


}
