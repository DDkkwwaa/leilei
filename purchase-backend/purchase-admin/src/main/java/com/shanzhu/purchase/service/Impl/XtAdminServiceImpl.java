package com.shanzhu.purchase.service.Impl;


import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.shanzhu.purchase.dao.XtAdminDao;
import com.shanzhu.purchase.dto.UpdateAdminPasswordParam;
import com.shanzhu.purchase.dto.adminRoleRelationDto;
import com.shanzhu.purchase.mapper.XtmdAdminMapper;
import com.shanzhu.purchase.mapper.XtmdAdminRoleRelationMapper;
import com.shanzhu.purchase.model.*;
import com.shanzhu.purchase.service.XtAdminService;
import com.shanzhu.purchase.service.XtRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class XtAdminServiceImpl implements XtAdminService {

    @Resource         //自定义
    private XtAdminDao AdminDao;

    @Resource       //mbg生成
    private XtmdAdminMapper AdminMapper;

    @Resource
    private XtRoleService roleService;

    @Resource
    private XtmdAdminRoleRelationMapper adminRoleRelationMapper;

    //通过用户名去获取用户信息
    @Override
    public List<XtmdAdmin> queryByUserName(String username) {
        XtmdAdminExample example = new XtmdAdminExample();
        example.createCriteria().andUserNameEqualTo(username);
        //查询用户信息
        return AdminMapper.selectByExample(example);
    }

    @Override
    public int create(XtmdAdmin admin) {
        //密码暂时是123456
        String TempPwd = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String TempEncode = bCryptPasswordEncoder.encode(TempPwd);
        admin.setPassword(TempEncode);
        return AdminMapper.insert(admin);
    }

    @Override
    public int update(XtmdAdmin admin) {
        //后期是否添加修改的时间
        return AdminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 直接加密后存入  因为已经校验了
     */
    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        String userName = param.getUsername();
        String newPassword = param.getNewPassword();
        String updatePassword = new BCryptPasswordEncoder().encode(newPassword);  //加密新密码
        return AdminDao.updatePasswordByuserName(userName, updatePassword);
    }

    @Override
    public int delete(Long id) {
        return AdminMapper.deleteByPrimaryKey(id);
    }

    /**
     * 这里使用list考虑到编程过程中的不唯一性
     */
    @Override
    public List<XtmdAdmin> getAdminInfo(String userName) {
        XtmdAdminExample example = new XtmdAdminExample();
        XtmdAdminExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameLike(userName);
        List<XtmdAdmin> adminList = AdminMapper.selectByExample(example);
        for (XtmdAdmin admin : adminList) {
            admin.setPassword(null);
        }
        return adminList;
    }

    @Override
    public boolean checkPassword(String username, String oldPassword) {
        boolean flag = false;
        //通过用户名查询用户密码，
        //对输入的加密后判断查询出的密码是否一致
        XtmdAdminExample example = new XtmdAdminExample();
        XtmdAdminExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(username);
        List<XtmdAdmin> adminList = AdminMapper.selectByExample(example);
        String dbPassword = adminList.get(0).getPassword(); //获取数据库中的密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(oldPassword, dbPassword);
        if (matches) {
            flag = true;
        }
        return flag;
    }

    /**
     * 查询用户分配的角色信息
     */
    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {
        List<Map<String, Object>> treeList = new ArrayList<>();
        /**
         * 1.查询所有的角色信息
         */
        List<XtmdRole> roles = roleService.list();   //查询出所有的角色信息
        if (roles != null && roles.size() > 0) {
            for (XtmdRole role : roles) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", role.getRoleId());
                map.put("label", role.getRoleName());
                treeList.add(map);
            }
        }
        //根据用户编号查询所拥有的角色编号
        List<Integer> menuIds = roleService.getRoleByAdminId(adminId);
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("checks", menuIds);
        resMap.put("treeData", treeList);
        return resMap;
    }


    @Override
    public int adminToRoleRelationship(adminRoleRelationDto adminRole) {
        //先删除原有关系 角色-菜单关系表
        if (adminRole.getAdminId() != null) {
            XtmdAdminRoleRelationExample example1 = new XtmdAdminRoleRelationExample();
            XtmdAdminRoleRelationExample.Criteria criteria = example1.createCriteria();
            criteria.andAdminIdEqualTo(adminRole.getAdminId());

            adminRoleRelationMapper.deleteByExample(example1);
        }
        List<Long> roleIds = adminRole.getRoleIds();
        if (roleIds != null && roleIds.size() > 0) {
            //批量插入新关系
            for (Long roleId : roleIds) {
                XtmdAdminRoleRelation relation = new XtmdAdminRoleRelation();
                relation.setAdminId(adminRole.getAdminId());
                relation.setRoleId(roleId);
                adminRoleRelationMapper.insert(relation);
            }
        }
        return roleIds.size();
    }


    /**
     * 分页获取数据
     */
    @Override
    public List<XtmdAdmin> getAdminList(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XtmdAdminExample example = new XtmdAdminExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andUserNameLike("%" + keyword + "%");
        }
        return AdminMapper.selectByExample(example);
    }

    /**
     * 用户名校验
     */
    @Override
    public boolean checkUserName(String userName) {
        XtmdAdminExample example = new XtmdAdminExample();
        XtmdAdminExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        //大于0 说明存在 否则不存在
        return AdminMapper.countByExample(example) > 0;
    }

    /**
     * 增加或者 修改
     */
    @Override
    public int updateOrAddById(XtmdAdmin admin) {
        if (admin.getId() != null && admin.getId() != 0) { //更新
            this.update(admin);
        } else {
            this.create(admin);
        }
        return 1;  //默认成功
    }

    /**
     * 密码初始化
     */
    @Override
    public int pwdInit(Long id) {
        String pwd = "123456";
        String initPwd = new BCryptPasswordEncoder().encode(pwd);  //加密密码
        XtmdAdmin admin = new XtmdAdmin();
        admin.setPassword(initPwd);  //初始化
        //查询条件
        XtmdAdminExample example = new XtmdAdminExample();
        example.createCriteria().andIdEqualTo(id);
        return AdminMapper.updateByExampleSelective(admin, example);
    }

    @Override
    public List getAdminAll() {
        ArrayList<Map> list = new ArrayList<>();
        List<XtmdAdmin> adminList = AdminMapper.selectByExample(new XtmdAdminExample());
        if (adminList != null) {
            for (XtmdAdmin admin : adminList) {
                Map<String, Object> hashMap = new HashMap<>();
                Long id = admin.getId();
                String userName = admin.getTrueName();  //获取真实姓名
                hashMap.put("id", id);
                hashMap.put("name", userName);
                list.add(hashMap);
            }
        }
        return list;
    }

}

