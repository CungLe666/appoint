package com.ruoyi.appoint.setting.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预约设置对象 appoint_setting
 * 
 * @author likang
 * @date 2023-03-11
 */
public class Setting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 技师id */
    @Excel(name = "技师id")
    private Long teacherId;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 最多预约人数 */
    @Excel(name = "最多预约人数")
    private Long maxPeople;

    /** 技师名称 */
    @Excel(name = "技师名称")
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setMaxPeople(Long maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Long getMaxPeople() {
        return maxPeople;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                .append("teacherId", getTeacherId()).append("startTime", getStartTime()).append("endTime", getEndTime())
                .append("maxPeople", getMaxPeople()).toString();
    }
}
