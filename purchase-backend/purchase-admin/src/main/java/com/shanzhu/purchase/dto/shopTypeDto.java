package com.shanzhu.purchase.dto;

import lombok.Data;

import java.util.List;

@Data
public class shopTypeDto {

    private List<String>  shopTypeList ;  //商品类型

    private List<Long> saleList;    //采购量

    private List<Long> stockList;   //库存量
}
