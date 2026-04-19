package com.shanzhu.purchase.service;


import com.shanzhu.purchase.model.JxmdSaleExit;

import java.util.List;

public interface JxSaleExitService {

    /**
     * 添加客户
     */
    int create(JxmdSaleExit saleExit);

    /**
     * 修改客户信息
     */
    int update( JxmdSaleExit saleExit);

    /**
     * 批量删除客户
     */
    int delete(Long id);

    /**
     * 获取所有客户
     */
    List<JxmdSaleExit > list();

    /**
     * 分页获取客户列表
     */
    List<JxmdSaleExit> list(String keyword, Integer pageSize, Integer pageNum);

    int addOrUpdate(JxmdSaleExit saleExit);
}
