package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JxmdPurchase implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "采购编号")
    private String number;

    @ApiModelProperty(value = "采购人")
    private String purchaseUser;

    @ApiModelProperty(value = "采购商品")
    private String shop;

    @ApiModelProperty(value = "商品所属类")
    private String shopType;

    @ApiModelProperty(value = "采购供应商")
    private String supplier;

    @ApiModelProperty(value = "采购数量")
    private Long quantity;

    @ApiModelProperty(value = "采购价格")
    private BigDecimal price;

    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "采购时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "状态 0 完成 1进行中")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "单位规格  个斤盒")
    private String specs;

    @ApiModelProperty(value = "是否存在，退货后不显示，可增加查询按钮")
    private Integer isDestroy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(String purchaseUser) {
        this.purchaseUser = purchaseUser;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Integer getIsDestroy() {
        return isDestroy;
    }

    public void setIsDestroy(Integer isDestroy) {
        this.isDestroy = isDestroy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", purchaseUser=").append(purchaseUser);
        sb.append(", shop=").append(shop);
        sb.append(", shopType=").append(shopType);
        sb.append(", supplier=").append(supplier);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", time=").append(time);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", specs=").append(specs);
        sb.append(", isDestroy=").append(isDestroy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}