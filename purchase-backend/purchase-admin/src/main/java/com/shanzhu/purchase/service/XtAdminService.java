package com.shanzhu.purchase.service;

import com.shanzhu.purchase.dto.UpdateAdminPasswordParam;
import com.shanzhu.purchase.dto.adminRoleRelationDto;
import com.shanzhu.purchase.model.XtmdAdmin;

import java.util.List;
import java.util.Map;

public interface XtAdminService {

    /**
     * 获取用户列表  因登陆名不唯一的话
     * @param username
     * @return
     */
    List<XtmdAdmin> queryByUserName(String username);

    /**
     * 添加用户信息
     * @param XtmdAdmin
     * @return
     */
    int create(XtmdAdmin XtmdAdmin);

    /**
     * 修改用户信息
     * @param  Admin
     * @return
     */
     int update( XtmdAdmin  Admin);

    /**
     * 修改用户密码
     * @param updatePasswordParam
     * @return
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);


    /**
     * 用户名 昵称  分页获取列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
   List<XtmdAdmin> getAdminList(String keyword, Integer pageSize, Integer pageNum);

    boolean checkUserName(String userName);

    int updateOrAddById(XtmdAdmin admin);

    int delete(Long id);

    /**
     * 获取登陆用户信息
     * @return
     */
    List<XtmdAdmin> getAdminInfo(String userName);

    boolean checkPassword(String username, String oldPassword);

    Map<String, Object> getRoleByAdminId(Long adminId);

    int adminToRoleRelationship(adminRoleRelationDto adminRole);

    int pwdInit(Long id);

    List getAdminAll();
}
