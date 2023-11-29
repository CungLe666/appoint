package com.ruoyi.appoint.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预约对象 appoint_order
 * 
 * @author likang
 * @date 2023-03-11
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;

    /** 项目id */
    private Long projectId;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    /** 技师id */
    private Long teacherId;

    /** 技师名称 */
    @Excel(name = "技师名称")
    private String teacherName;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 图片 */
    private String picUrl;

    /** 预约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appointTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal totalAmount;

    /** 时间id */
    private Long timeId;

    /** 时间 */
    @Excel(name = "时间")
    private String time;

    /** 用户 */
    @Excel(name = "用户name")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                .append("orderNumber", getOrderNumber()).append("projectId", getProjectId())
                .append("userId", getUserId()).append("teacherId", getTeacherId())
                .append("teacherName", getTeacherName()).append("projectName", getProjectName())
                .append("picUrl", getPicUrl()).append("appointTime", getAppointTime()).append("status", getStatus())
                .append("totalAmount", getTotalAmount()).append("timeId", getTimeId()).append("time", getTime())
                .append("createTime", getCreateTime()).toString();
    }
}
