package com.ruoyi.appoint.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.appoint.project.domain.Project;
import com.ruoyi.appoint.project.mapper.ProjectMapper;
import com.ruoyi.appoint.project.service.IProjectService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

/**
 * 项目Service业务层处理
 * 
 * @author likang
 * @date 2023-03-11
 */
@Service
public class ProjectServiceImpl implements IProjectService
{
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 查询项目
     * 
     * @param id
     *            项目主键
     * @return 项目
     */
    @Override
    public Project selectProjectById(Long id) {
        return projectMapper.selectProjectById(id);
    }

    /**
     * 查询项目列表
     * 
     * @param project
     *            项目
     * @return 项目
     */
    @Override
    public List<Project> selectProjectList(Project project) {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增项目
     * 
     * @param project
     *            项目
     * @return 结果
     */
    @Override
    public int insertProject(Project project) {
        project.setCreateTime(DateUtils.getNowDate());
        return projectMapper.insertProject(project);
    }

    /**
     * 修改项目
     * 
     * @param project
     *            项目
     * @return 结果
     */
    @Override
    public int updateProject(Project project) {
        return projectMapper.updateProject(project);
    }

    /**
     * 批量删除项目
     * 
     * @param ids
     *            需要删除的项目主键
     * @return 结果
     */
    @Override
    public int deleteProjectByIds(String ids) {
        return projectMapper.deleteProjectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除项目信息
     * 
     * @param id
     *            项目主键
     * @return 结果
     */
    @Override
    public int deleteProjectById(Long id) {
        return projectMapper.deleteProjectById(id);
    }

    @Override
    public List<Project> queryProjectList(Map<String, Object> params) {
        return projectMapper.queryProjectList(params);
    }

}
