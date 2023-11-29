package com.ruoyi.appoint.evaluation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.appoint.member.domain.Member;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预约评价对象 appoint_evaluation
 * 
 * @author likang
 * @date 2023-03-11
 */
public class Evaluation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long memberId;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String content;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

    private Member member;

    public Member getMember() {
        return member;
    }

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 技师名称 */
    @Excel(name = "技师名称")
    private String teacherName;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                .append("orderId", getOrderId()).append("memberId", getMemberId()).append("content", getContent())
                .append("projectId", getProjectId()).append("createTime", getCreateTime()).toString();
    }
}
