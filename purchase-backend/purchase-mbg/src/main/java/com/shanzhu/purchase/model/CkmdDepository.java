package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CkmdDepository implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "仓库编号")
    private String number;

    @ApiModelProperty(value = "仓库名称")
    private String name;

    @ApiModelProperty(value = "仓库负责人")
    private String head;

    @ApiModelProperty(value = "仓库电话")
    private String storePhone;

    @ApiModelProperty(value = "仓库地址")
    private String address;

    @ApiModelProperty(value = "库存总容量")
    private Long stockTotal;

    @ApiModelProperty(value = "剩余容量")
    private Long surplus;

    @ApiModelProperty(value = "面积单位")
    private String area;

    @ApiModelProperty(value = "0 正常 1 停用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime buildDate;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(Long stockTotal) {
        this.stockTotal = stockTotal;
    }

    public Long getSurplus() {
        return surplus;
    }

    public void setSurplus(Long surplus) {
        this.surplus = surplus;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(LocalDateTime buildDate) {
        this.buildDate = buildDate;
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
        sb.append(", number=").append(number);
        sb.append(", name=").append(name);
        sb.append(", head=").append(head);
        sb.append(", storePhone=").append(storePhone);
        sb.append(", address=").append(address);
        sb.append(", stockTotal=").append(stockTotal);
        sb.append(", surplus=").append(surplus);
        sb.append(", area=").append(area);
        sb.append(", status=").append(status);
        sb.append(", buildDate=").append(buildDate);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}