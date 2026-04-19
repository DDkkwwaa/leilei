package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class JcmdSupplier implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "供应商名")
    private String supplierName;

    @ApiModelProperty(value = "负责人")
    private String head;

    @ApiModelProperty(value = "供应商电话")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "商品名称")
    private String shopName;

    @ApiModelProperty(value = "银行")
    private String branch;

    @ApiModelProperty(value = "银行账号")
    private String branchAccount;

    @ApiModelProperty(value = "供应商法人代表")
    private String supplierProxy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "其他")
    private String other;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchAccount() {
        return branchAccount;
    }

    public void setBranchAccount(String branchAccount) {
        this.branchAccount = branchAccount;
    }

    public String getSupplierProxy() {
        return supplierProxy;
    }

    public void setSupplierProxy(String supplierProxy) {
        this.supplierProxy = supplierProxy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", head=").append(head);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", shopName=").append(shopName);
        sb.append(", branch=").append(branch);
        sb.append(", branchAccount=").append(branchAccount);
        sb.append(", supplierProxy=").append(supplierProxy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", other=").append(other);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}