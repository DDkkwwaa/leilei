package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JxmdSale implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "销售编号")
    private String saleNumber;

    @ApiModelProperty(value = "销售人")
    private String saleUser;

    @ApiModelProperty(value = "销售商品")
    private String shop;

    @ApiModelProperty(value = "商品所属类")
    private String shopType;

    @ApiModelProperty(value = "销售客户，需要改成客户英文名")
    private String supplier;

    @ApiModelProperty(value = "数量")
    private Long num;

    @ApiModelProperty(value = "单位规格  个斤盒")
    private String specs;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "销售时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "状态 0 完成 1进行中")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getSaleUser() {
        return saleUser;
    }

    public void setSaleUser(String saleUser) {
        this.saleUser = saleUser;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", saleNumber=").append(saleNumber);
        sb.append(", saleUser=").append(saleUser);
        sb.append(", shop=").append(shop);
        sb.append(", shopType=").append(shopType);
        sb.append(", supplier=").append(supplier);
        sb.append(", num=").append(num);
        sb.append(", specs=").append(specs);
        sb.append(", price=").append(price);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", time=").append(time);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}