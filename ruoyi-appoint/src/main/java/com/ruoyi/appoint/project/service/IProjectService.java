package com.ruoyi.appoint.project.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.appoint.project.domain.Project;

/**
 * 项目Service接口
 * 
 * @author likang
 * @date 2023-03-11
 */
public interface IProjectService
{
    /**
     * 查询项目
     * 
     * @param id
     *            项目主键
     * @return 项目
     */
    public Project selectProjectById(Long id);

    /**
     * 查询项目列表
     * 
     * @param project
     *            项目
     * @return 项目集合
     */
    public List<Project> selectProjectList(Project project);

    /**
     * 新增项目
     * 
     * @param project
     *            项目
     * @return 结果
     */
    public int insertProject(Project project);

    /**
     * 修改项目
     * 
     * @param project
     *            项目
     * @return 结果
     */
    public int updateProject(Project project);

    /**
     * 批量删除项目
     * 
     * @param ids
     *            需要删除的项目主键集合
     * @return 结果
     */
    public int deleteProjectByIds(String ids);

    /**
     * 删除项目信息
     * 
     * @param id
     *            项目主键
     * @return 结果
     */
    public int deleteProjectById(Long id);

    public List<Project> queryProjectList(Map<String, Object> params);

}
