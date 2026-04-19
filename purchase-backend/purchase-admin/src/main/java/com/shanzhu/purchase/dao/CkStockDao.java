package com.shanzhu.purchase.dao;

import com.shanzhu.purchase.dto.ShopAndSaleAndStockAndPurchaseDto;
import com.shanzhu.purchase.model.CkmdStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CkStockDao {

  int  addQuantityByTwoName(@Param("quantity") Long quantity ,@Param("shopName") String shopName, @Param("depositoryName") String depositoryName);

  int  reduceQuantityByTwoName(@Param("quantity") Long quantity ,@Param("shopName") String shopName, @Param("depositoryName") String depositoryName);

  List<CkmdStock> getStockByTwoName(String shopName, String depositoryName);

  List<ShopAndSaleAndStockAndPurchaseDto> getNumberToShopAndStockAndPurchase();
}
