package com.shanzhu.purchase.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class XtmdAdmin implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "部门id或者名称")
    private String dept;

    @ApiModelProperty(value = "账户")
    private String userName;

    @ApiModelProperty(value = "真实姓名")
    private String trueName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "头像路径")
    private String icon;

    @ApiModelProperty(value = "0正常 1停用")
    private Integer status;

    @ApiModelProperty(value = "邮箱")
    private String eMail;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后登录ip")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime loginDate;

    @ApiModelProperty(value = "0男 1女 ")
    private Integer sex;

    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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
        sb.append(", dept=").append(dept);
        sb.append(", userName=").append(userName);
        sb.append(", trueName=").append(trueName);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", icon=").append(icon);
        sb.append(", status=").append(status);
        sb.append(", eMail=").append(eMail);
        sb.append(", phone=").append(phone);
        sb.append(", createTime=").append(createTime);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginDate=").append(loginDate);
        sb.append(", sex=").append(sex);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}