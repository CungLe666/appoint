package com.ruoyi.appoint.project.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.appoint.project.domain.Project;

/**
 * 项目Mapper接口
 * 
 * @author likang
 * @date 2023-03-11
 */
public interface ProjectMapper
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
     * 删除项目
     * 
     * @param id
     *            项目主键
     * @return 结果
     */
    public int deleteProjectById(Long id);

    /**
     * 批量删除项目
     * 
     * @param ids
     *            需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectByIds(String[] ids);

    public List<Project> queryProjectList(Map<String, Object> params);

}
