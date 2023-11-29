package com.ruoyi.appoint.teacher.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 技师对象 appoint_teacher
 * 
 * @author likang
 * @date 2023-03-11
 */
public class Teacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 头像 */
    @Excel(name = "头像")
    private String picUrl;

    /** 技师名称 */
    @Excel(name = "技师名称")
    private String realName;

    /** 电话 */
    @Excel(name = "电话")
    private String mobile;

    /** 特长 */
    @Excel(name = "特长")
    private String specialty;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPicUrl(String picUrl) 
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl() 
    {
        return picUrl;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setSpecialty(String specialty) 
    {
        this.specialty = specialty;
    }

    public String getSpecialty() 
    {
        return specialty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("picUrl", getPicUrl())
            .append("realName", getRealName())
            .append("mobile", getMobile())
            .append("specialty", getSpecialty())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
