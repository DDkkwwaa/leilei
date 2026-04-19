package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CkmdDepositoryOut implements Serializable {
    @ApiModelProperty(value = "出库id")
    private Long id;

    @ApiModelProperty(value = "来源 id")
    private Long sourceNumber;

    @ApiModelProperty(value = "出库编号")
    private Long outId;

    @ApiModelProperty(value = "出库仓库")
    private String depository;

    @ApiModelProperty(value = "商品名称")
    private String shopName;

    @ApiModelProperty(value = "出库价格")
    private BigDecimal shopPrice;

    @ApiModelProperty(value = "商品数量")
    private Long shopNumber;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "规格")
    private String specs;

    @ApiModelProperty(value = "出库时间")
    private LocalDateTime date;

    @ApiModelProperty(value = "出库用户")
    private String outUser;

    @ApiModelProperty(value = "客户姓名")
    private String shopSupplier;

    @ApiModelProperty(value = "是否出库 0 出库 1不出库")
    private Integer status;

    @ApiModelProperty(value = "0 审核 1表示未审核")
    private Integer outInspection;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(Long sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getOutUser() {
        return outUser;
    }

    public void setOutUser(String outUser) {
        this.outUser = outUser;
    }

    public String getShopSupplier() {
        return shopSupplier;
    }

    public void setShopSupplier(String shopSupplier) {
        this.shopSupplier = shopSupplier;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOutInspection() {
        return outInspection;
    }

    public void setOutInspection(Integer outInspection) {
        this.outInspection = outInspection;
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
        sb.append(", outId=").append(outId);
        sb.append(", depository=").append(depository);
        sb.append(", shopName=").append(shopName);
        sb.append(", shopPrice=").append(shopPrice);
        sb.append(", shopNumber=").append(shopNumber);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", specs=").append(specs);
        sb.append(", date=").append(date);
        sb.append(", outUser=").append(outUser);
        sb.append(", shopSupplier=").append(shopSupplier);
        sb.append(", status=").append(status);
        sb.append(", outInspection=").append(outInspection);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}