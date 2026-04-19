package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CkmdDepositoryIn implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "来源 可有也可无")
    private Integer sourceNumber;

    @ApiModelProperty(value = "入库编号")
    private Long inId;

    @ApiModelProperty(value = "入库仓库名")
    private String depository;

    @ApiModelProperty(value = "入库商名")
    private String shopName;

    @ApiModelProperty(value = "入库商品单价")
    private BigDecimal shopPrice;

    @ApiModelProperty(value = "入库商品数量")
    private Long shopNumber;

    @ApiModelProperty(value = "入库商品总价")
    private BigDecimal priceTotal;

    @ApiModelProperty(value = "单位规格 [个斤盒]")
    private String specs;

    @ApiModelProperty(value = "入库日期")
    private LocalDateTime date;

    @ApiModelProperty(value = "入库人")
    private String inUser;

    @ApiModelProperty(value = "供应商名")
    private String shopSupplier;

    @ApiModelProperty(value = "0已质检 1 未质检")
    private Integer isInspection;

    @ApiModelProperty(value = "0 已入库 1 未入库")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(Integer sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public Long getInId() {
        return inId;
    }

    public void setInId(Long inId) {
        this.inId = inId;
    }

    public String getDepository() {
        return depository;
    }

    public void setDepository(String depository) {
        this.depository = depository;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Long getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(Long shopNumber) {
        this.shopNumber = shopNumber;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getInUser() {
        return inUser;
    }

    public void setInUser(String inUser) {
        this.inUser = inUser;
    }

    public String getShopSupplier() {
        return shopSupplier;
    }

    public void setShopSupplier(String shopSupplier) {
        this.shopSupplier = shopSupplier;
    }

    public Integer getIsInspection() {
        return isInspection;
    }

    public void setIsInspection(Integer isInspection) {
        this.isInspection = isInspection;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sourceNumber=").append(sourceNumber);
        sb.append(", inId=").append(inId);
        sb.append(", depository=").append(depository);
        sb.append(", shopName=").append(shopName);
        sb.append(", shopPrice=").append(shopPrice);
        sb.append(", shopNumber=").append(shopNumber);
        sb.append(", priceTotal=").append(priceTotal);
        sb.append(", specs=").append(specs);
        sb.append(", date=").append(date);
        sb.append(", inUser=").append(inUser);
        sb.append(", shopSupplier=").append(shopSupplier);
        sb.append(", isInspection=").append(isInspection);
        sb.append(", status=").append(status);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}