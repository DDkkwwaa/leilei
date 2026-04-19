package com.shanzhu.purchase.dao;


import com.shanzhu.purchase.model.XtmdAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface XtAdminDao {

    XtmdAdmin getAdminByUsername(@Param("LonginName") String LonginName);

    List<XtmdAdmin> getAdminList();

    int addAdmin(XtmdAdmin xtmdAdmin);

    int updateAdmin(XtmdAdmin xtmdAdmin);

    int deleAdminById(long id);

    int deleAdminList(@Param("list") List xtmdAdminList);

    int updatePasswordByuserName(@Param("userName") String userName, @Param("updatePassword") String updatePassword);
}
