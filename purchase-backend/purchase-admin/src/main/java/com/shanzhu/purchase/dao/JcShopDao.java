package com.shanzhu.purchase.dao;

import com.shanzhu.purchase.model.JcmdShopType;

public interface JcShopDao {
    JcmdShopType selectShopTypeByName(String shopName);
}
