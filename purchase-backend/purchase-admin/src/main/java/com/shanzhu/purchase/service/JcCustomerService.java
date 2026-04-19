package com.shanzhu.purchase.service;

import com.shanzhu.purchase.model.JcmdCustomer;

import java.util.List;

public interface JcCustomerService {

    /**
     * 添加客户
     */
    int create(JcmdCustomer customer);

    /**
     * 修改客户信息
     */
    int update(  JcmdCustomer customer);

    /**
     * 批量删除客户
     */
    int delete( long id);

    /**
     * 获取所有客户
     */
    List<JcmdCustomer > list();

    /**
     * 分页获取客户列表
     */
    List<JcmdCustomer> list(String keyword, Integer pageNum, Integer pageSize);


    List getIDAndCustomerName();
}
