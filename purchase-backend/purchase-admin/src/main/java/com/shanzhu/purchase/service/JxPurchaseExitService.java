package com.shanzhu.purchase.service;

import com.shanzhu.purchase.model.JxmdPurchaseExit;

import java.util.List;

public interface JxPurchaseExitService {

    /**
     * 添加客户
     */
    int create(JxmdPurchaseExit purchaseExit);

    /**
     * 修改客户信息
     */
    int update( JxmdPurchaseExit purchaseExit);

    /**
     * 批量删除客户
     */
    int delete(Long id);

    /**
     * 获取所有客户
     */
    List<JxmdPurchaseExit > list();

    /**
     * 分页获取客户列表
     */
    List<JxmdPurchaseExit> list(String keyword, Integer pageSize, Integer pageNum);

    int addOrUpdate(JxmdPurchaseExit purchaseExit);
}
