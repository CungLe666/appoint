package com.ruoyi.appoint.teacher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.appoint.teacher.domain.Teacher;
import com.ruoyi.appoint.teacher.mapper.TeacherMapper;
import com.ruoyi.appoint.teacher.service.ITeacherService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

/**
 * 技师Service业务层处理
 * 
 * @author likang
 * @date 2023-03-11
 */
@Service
public class TeacherServiceImpl implements ITeacherService
{
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 查询技师
     * 
     * @param id
     *            技师主键
     * @return 技师
     */
    @Override
    public Teacher selectTeacherById(Long id) {
        return teacherMapper.selectTeacherById(id);
    }

    /**
     * 查询技师列表
     * 
     * @param teacher
     *            技师
     * @return 技师
     */
    @Override
    public List<Teacher> selectTeacherList(Teacher teacher) {
        return teacherMapper.selectTeacherList(teacher);
    }

    /**
     * 新增技师
     * 
     * @param teacher
     *            技师
     * @return 结果
     */
    @Override
    public int insertTeacher(Teacher teacher) {
        teacher.setCreateTime(DateUtils.getNowDate());
        return teacherMapper.insertTeacher(teacher);
    }

    /**
     * 修改技师
     * 
     * @param teacher
     *            技师
     * @return 结果
     */
    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 批量删除技师
     * 
     * @param ids
     *            需要删除的技师主键
     * @return 结果
     */
    @Override
    public int deleteTeacherByIds(String ids) {
        return teacherMapper.deleteTeacherByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除技师信息
     * 
     * @param id
     *            技师主键
     * @return 结果
     */
    @Override
    public int deleteTeacherById(Long id) {
        return teacherMapper.deleteTeacherById(id);
    }

    @Override
    public List<Teacher> selectTeacherName() {
        return teacherMapper.selectTeacherName();
    }

    @Override
    public List<Teacher> quaryTeacherList() {
        return teacherMapper.quaryTeacherList();
    }

    @Override
    public List<Teacher> quaryTeacherListAll() {
        return teacherMapper.quaryTeacherListAll();
    }
}
