package com.shanzhu.purchase.dto;


import lombok.Data;

/**
 * 大屏 商品 销售量 采购量 库存量
 */
@Data
public class ShopAndSaleAndStockAndPurchaseDto {

    private  String shopName; //商品

    private  Long StockNumber; // 库存量

    private  Long saleNumber; //销售量

    private  Long  PurchaseNumber;//采购量
}
