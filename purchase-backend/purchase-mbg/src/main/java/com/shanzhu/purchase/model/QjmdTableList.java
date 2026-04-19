package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class QjmdTableList implements Serializable {
    private Long id;

    @ApiModelProperty(value = "表名")
    private String tablename;

    @ApiModelProperty(value = "业务模块")
    private String md;

    @ApiModelProperty(value = "描述")
    private String describe;

    @ApiModelProperty(value = "表的顺序即业务-菜单显示顺序")
    private Long sort;

    @ApiModelProperty(value = "模块业务的子业务显示顺序")
    private Long children;

    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getChildren() {
        return children;
    }

    public void setChildren(Long children) {
        this.children = children;
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
        sb.append(", tablename=").append(tablename);
        sb.append(", md=").append(md);
        sb.append(", describe=").append(describe);
        sb.append(", sort=").append(sort);
        sb.append(", children=").append(children);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}