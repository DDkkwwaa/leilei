package com.shanzhu.purchase.dto;

import com.shanzhu.purchase.model.JxmdPurchase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseToDepositoryIn   {

    @ApiModelProperty(value = "入库仓库名",required = true)
    private String depositoryName; //入库仓库

    @ApiModelProperty(value = "入库的采购单" )
    private List<JxmdPurchase> purchaseList;
}
