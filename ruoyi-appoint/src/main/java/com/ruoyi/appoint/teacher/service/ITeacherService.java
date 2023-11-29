package com.ruoyi.appoint.teacher.service;

import java.util.List;

import com.ruoyi.appoint.teacher.domain.Teacher;

/**
 * 技师Service接口
 * 
 * @author likang
 * @date 2023-03-11
 */
public interface ITeacherService
{
    /**
     * 查询技师
     * 
     * @param id
     *            技师主键
     * @return 技师
     */
    public Teacher selectTeacherById(Long id);

    /**
     * 查询技师列表
     * 
     * @param teacher
     *            技师
     * @return 技师集合
     */
    public List<Teacher> selectTeacherList(Teacher teacher);

    /**
     * 新增技师
     * 
     * @param teacher
     *            技师
     * @return 结果
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 修改技师
     * 
     * @param teacher
     *            技师
     * @return 结果
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 批量删除技师
     * 
     * @param ids
     *            需要删除的技师主键集合
     * @return 结果
     */
    public int deleteTeacherByIds(String ids);

    /**
     * 删除技师信息
     * 
     * @param id
     *            技师主键
     * @return 结果
     */
    public int deleteTeacherById(Long id);

    public List<Teacher> selectTeacherName();

    public List<Teacher> quaryTeacherList();

    public List<Teacher> quaryTeacherListAll();
}
