package com.shanzhu.purchase.service.Impl;

import com.shanzhu.purchase.model.XtmdAdmin;
import com.shanzhu.purchase.model.XtmdRole;
import com.shanzhu.purchase.service.XtAdminService;
import com.shanzhu.purchase.service.XtRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证校验的方法
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    XtAdminService sysUserService;

    @Resource
    XtRoleService sysRoleService;

    /**
     * 完成账号的校验
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.需要根据账号查询
        List<XtmdAdmin> list = sysUserService.queryByUserName(username);
        if (list != null && list.size() == 1) {
            // 账号是存在的
            XtmdAdmin sysUser = list.get(0);
            List<XtmdRole> sysRoles = sysRoleService.queryByUserId(sysUser.getId());
            List<GrantedAuthority> listRole = new ArrayList<>();
            if (sysRoles != null && sysRoles.size() > 0) {
                for (XtmdRole sysRole : sysRoles) {
                    listRole.add(new SimpleGrantedAuthority(sysRole.getRoleName()));
                }
            }
            // 密码模拟的是就数据库查询出来  登陆的 不是真实的名
            return new User(sysUser.getUserName(), sysUser.getPassword(), listRole);
        }
        return null;
    }

}
