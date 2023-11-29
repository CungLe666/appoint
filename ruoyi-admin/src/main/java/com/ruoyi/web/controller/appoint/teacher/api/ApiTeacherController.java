package com.ruoyi.web.controller.appoint.teacher.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.teacher.domain.Teacher;
import com.ruoyi.appoint.teacher.service.ITeacherService;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/api/teacher/")
public class ApiTeacherController
{

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("list")
    public AjaxResult list() {
        List<Teacher> teacherList = teacherService.quaryTeacherList();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("teacherList", teacherList);
        return AjaxResult.success(data);
    }

    @GetMapping("listall")
    public AjaxResult listAll() {
        List<Teacher> teacherList = teacherService.quaryTeacherListAll();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("teacherList", teacherList);
        return AjaxResult.success(data);
    }

    @GetMapping("detail")
    public AjaxResult detail(String id) {
        Teacher teacher = teacherService.selectTeacherById(Long.valueOf(id));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("teacher", teacher);
        return AjaxResult.success(data);
    }
}
