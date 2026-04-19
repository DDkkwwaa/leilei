package com.shanzhu.purchase.service.Impl;


import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.XtMenuDao;
import com.shanzhu.purchase.dto.ShowMenu;
import com.shanzhu.purchase.mapper.XtmdMenuMapper;
import com.shanzhu.purchase.model.XtmdMenu;
import com.shanzhu.purchase.model.XtmdMenuExample;
import com.shanzhu.purchase.service.XtMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class XtMenuServiceImpl implements XtMenuService {

    @Resource
    private XtmdMenuMapper menuMapper;

    @Resource
    private XtMenuDao menuDao;

    @Override
    public int create(XtmdMenu menu) {
        menu.setCreateTime(LocalDateTime.now());
        return menuMapper.insert(menu);
    }

    @Override
    public int update(Long id, XtmdMenu menu) {
        menu.setId(id);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int delete(List<Long> ids) {
        XtmdMenuExample example = new XtmdMenuExample();
        example.createCriteria().andIdIn(ids);
        int count = menuMapper.deleteByExample(example);
        return count;
    }

    @Override
    public List<XtmdMenu> list() {
        return menuMapper.selectByExample(new XtmdMenuExample());
    }

    @Override
    public List<XtmdMenu> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XtmdMenuExample example = new XtmdMenuExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return menuMapper.selectByExample(example);
    }

    /**
     * 查询所有的父菜单信息 一级菜单
     */
    @Override
    public List<XtmdMenu> ListMenuParent() {
        List<XtmdMenu> xtmdMenus = menuDao.ListMenuParent();
        return xtmdMenus;
    }

    /**
     * 查询子菜单
     *
     * @param id 父菜单id
     * @return
     */
    @Override
    public List<XtmdMenu> childMenu(Long id) {
        List<XtmdMenu> ChildMenus = menuDao.childMenu(id);
        return ChildMenus;
    }

    @Override
    public List<Integer> queryMenuByRoleId(Long roleId) {
        return menuDao.queryMenuByRoleId(roleId);
    }

    /**
     * 获取用户所具有的菜单
     *
     * @return
     */
    @Override
    public List<ShowMenu> selectShowMenuByAdminName() {
        //查询当前登陆用户的所有父菜单  用户--角色-->菜单
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) token.getPrincipal();
        //先查询出该用户具有的父菜单信息--->根据查询出的父菜单查询子菜单信息
        List<XtmdMenu> xtMenusList = this.menuDao.selectShowMenuByAdminName(userName);
        if (xtMenusList != null && xtMenusList.size() > 0) {
            return xtMenusList.stream().map(item -> {  //父菜单
                ShowMenu showMenu = new ShowMenu();
                showMenu.setName(item.getName());
                showMenu.setLabel(item.getTitle());
                showMenu.setIcon(item.getIcon());
                showMenu.setUrl(item.getUrl());
                showMenu.setPath(item.getPath());
                //查询 该用户的二级菜单
                List<XtmdMenu> menuChildList = this.menuDao.selectChildMenuByAdminName(userName, item.getId());
                if (menuChildList != null && menuChildList.size() > 0) {
                    List<ShowMenu> showChildMenus = new ArrayList<>();   //子菜单List
                    for (XtmdMenu menu : menuChildList) {       //每一个子菜单记录封装为一个对象
                        ShowMenu childMenu = new ShowMenu();   //Dto对象
                        childMenu.setName(menu.getName());
                        childMenu.setLabel(menu.getTitle());
                        childMenu.setIcon(menu.getIcon());
                        childMenu.setUrl(menu.getUrl());
                        childMenu.setPath(menu.getPath());
                        showChildMenus.add(childMenu);
                    }
                    showMenu.setChildren(showChildMenus);  //父菜单和子菜单封装
                }
                return showMenu;
            }).collect(Collectors.toList());
        }
        return null;
    }


}
