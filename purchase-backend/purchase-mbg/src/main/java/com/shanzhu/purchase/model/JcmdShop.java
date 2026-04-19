package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JcmdShop implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "商品名")
    private String name;

    @ApiModelProperty(value = "商品科类")
    private Long parentId;

    @ApiModelProperty(value = "商品数量")
    private Long shopNumber;

    @ApiModelProperty(value = "规格")
    private String specs;

    @ApiModelProperty(value = "市场价格")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime buildDate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(Long shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public LocalDateTime getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(LocalDateTime buildDate) {
        this.buildDate = buildDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
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
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append(", shopNumber=").append(shopNumber);
        sb.append(", specs=").append(specs);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", buildDate=").append(buildDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}