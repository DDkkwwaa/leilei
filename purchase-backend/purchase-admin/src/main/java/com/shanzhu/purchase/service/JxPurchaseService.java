package com.shanzhu.purchase.service;

import com.shanzhu.purchase.model.JxmdPurchase;

import java.util.List;

public interface JxPurchaseService {

    /**
     * 添加客户
     */
    int create(JxmdPurchase purchase);

    /**
     * 修改客户信息
     */
    int update( JxmdPurchase purchase);

    /**
     * 批量删除客户
     */
    int delete(Long id);

    /**
     * 获取所有客户
     */
    List<JxmdPurchase > list();

    /**
     * 分页获取客户列表
     */
    List<JxmdPurchase> list(String keyword, Integer pageSize, Integer pageNum);

    int addOrUpdate(JxmdPurchase purchase);

    /**
     * 采购 入库操作
     * @param
     * @return
     */
    int putStock(String depositoryName,JxmdPurchase purchaseList);

    List getNumberAndSupplierName();

    int checkAndExitGoods(JxmdPurchase purchase);

}
