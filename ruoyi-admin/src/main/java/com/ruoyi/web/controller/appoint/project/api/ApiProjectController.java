package com.ruoyi.web.controller.appoint.project.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.project.domain.Project;
import com.ruoyi.appoint.project.service.IProjectService;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/api/project/")
public class ApiProjectController
{

    @Autowired
    private IProjectService projectService;

    @GetMapping("list")
    public AjaxResult list(@RequestParam Map<String, Object> params) {
        Map<String, Object> quaryMap = new HashMap<>();

        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        String projectName = params.get("projectName") == null ? "" : params.get("projectName").toString();
        int categoryId = params.get("categoryId") == null ? -1 : Integer.parseInt(params.get("categoryId").toString());

        quaryMap.put("offset", (page - 1) * limit);
        quaryMap.put("page", page);
        quaryMap.put("limit", limit);
        quaryMap.put("projectName", projectName);
        quaryMap.put("categoryId", categoryId);

        List<Project> projectList = projectService.queryProjectList(quaryMap);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("projectList", projectList);
        return AjaxResult.success(data);
    }

    @GetMapping("detail")
    public AjaxResult detail(String id) {
        Project project = projectService.selectProjectById(Long.valueOf(id));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("project", project);
        return AjaxResult.success(data);
    }
}
