package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CkmdStock implements Serializable {
    @ApiModelProperty(value = "序号 商品库存id")
    private Long id;

    @ApiModelProperty(value = "商品")
    private String shop;

    @ApiModelProperty(value = "商品类型")
    private String shopType;

    @ApiModelProperty(value = "库存量")
    private Long quantity;

    @ApiModelProperty(value = "规格 斤 、千克、个")
    private String speces;

    @ApiModelProperty(value = "仓库名")
    private String depository;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSpeces() {
        return speces;
    }

    public void setSpeces(String speces) {
        this.speces = speces;
    }

    public String getDepository() {
        return depository;
    }

    public void setDepository(String depository) {
        this.depository = depository;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", shop=").append(shop);
        sb.append(", shopType=").append(shopType);
        sb.append(", quantity=").append(quantity);
        sb.append(", speces=").append(speces);
        sb.append(", depository=").append(depository);
        sb.append(", address=").append(address);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}