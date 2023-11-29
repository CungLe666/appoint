package com.ruoyi.web.controller.appoint.project;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.appoint.category.service.ICategoryService;
import com.ruoyi.appoint.evaluation.service.IEvaluationService;
import com.ruoyi.appoint.project.domain.Project;
import com.ruoyi.appoint.project.service.IProjectService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 项目Controller
 * 
 * @author likang
 * @date 2023-03-11
 */
@Controller
@RequestMapping("/appoint/project")
public class ProjectController extends BaseController
{
    private String prefix = "appoint/project";

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IEvaluationService evaluationService;

    @RequiresPermissions("appoint:project:view")
    @GetMapping()
    public String project() {
        return prefix + "/project";
    }

    /**
     * 查询项目列表
     */
    @RequiresPermissions("appoint:project:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Project project) {
        startPage();
        List<Project> list = projectService.selectProjectList(project);

        for (Project project2 : list) {
            project2.setCategoryName(categoryService.selectCategoryById(project2.getCategoryId()).getCategoryName());
        }

        return getDataTable(list);
    }

    /**
     * 导出项目列表
     */
    @RequiresPermissions("appoint:project:export")
    @Log(title = "项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Project project) {
        List<Project> list = projectService.selectProjectList(project);
        ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
        return util.exportExcel(list, "项目数据");
    }

    /**
     * 新增项目
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("categoryList", categoryService.selectCategoryName());
        return prefix + "/add";
    }

    /**
     * 新增保存项目
     */
    @RequiresPermissions("appoint:project:add")
    @Log(title = "项目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Project project) {
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目
     */
    @RequiresPermissions("appoint:project:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Project project = projectService.selectProjectById(id);
        mmap.put("project", project);
        mmap.put("categoryList", categoryService.selectCategoryName());
        return prefix + "/edit";
    }

    /**
     * 修改保存项目
     */
    @RequiresPermissions("appoint:project:edit")
    @Log(title = "项目", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Project project) {
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目
     */
    @RequiresPermissions("appoint:project:remove")
    @Log(title = "项目", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {

        String[] strs = ids.split(",");
        for (String projectId : strs) {
            evaluationService.deleteEvaluationByProjectid(projectId);
        }

        return toAjax(projectService.deleteProjectByIds(ids));
    }
}
