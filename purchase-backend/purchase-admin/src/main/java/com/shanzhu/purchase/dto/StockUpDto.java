package com.shanzhu.purchase.dto;

import lombok.Data;

@Data
public class StockUpDto {

    private  int flag;  //表示0 增加

    private  String shopName;  //商品名称

    private  String depositoryName;  //仓库名称

    private  Long quantity;  //数量
}
