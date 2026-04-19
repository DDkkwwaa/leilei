package com.shanzhu.purchase.service;

import com.shanzhu.purchase.model.JcmdShop;
import com.shanzhu.purchase.model.JcmdShopType;

import java.util.ArrayList;
import java.util.List;

public interface JcShopService {

    /**
     * 添加客户
     */
    int create( JcmdShop shop);

    /**
     * 修改客户信息
     */
    int update( JcmdShop shop);


    /**
     * 判断是添加还是修改
     */
    int  updateOrAddById( JcmdShop shop);


    /**
     *  删除客户
     */
    int delete(Long id);

    /**
     * 获取所有客户
     */
    List<JcmdShop > list();

    /**
     * 分页获取客户列表
     */
    List<JcmdShop> list(String keyword, Integer pageNum, Integer pageSize);


    /**
     * 获取商品类型
     * @param shopName
     * @return
     */
    JcmdShopType selectShopTypeByName(String shopName);

    ArrayList<Object> getShopNameAll();

    List<JcmdShopType> selectShopTypeList();
}
