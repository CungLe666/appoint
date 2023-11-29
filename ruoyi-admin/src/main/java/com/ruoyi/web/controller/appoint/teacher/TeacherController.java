package com.ruoyi.web.controller.appoint.teacher;

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

import com.ruoyi.appoint.setting.service.ISettingService;
import com.ruoyi.appoint.teacher.domain.Teacher;
import com.ruoyi.appoint.teacher.service.ITeacherService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 技师Controller
 * 
 * @author likang
 * @date 2023-03-11
 */
@Controller
@RequestMapping("/appoint/teacher")
public class TeacherController extends BaseController
{
    private String prefix = "appoint/teacher";

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ISettingService settingService;

    @RequiresPermissions("appoint:teacher:view")
    @GetMapping()
    public String teacher() {
        return prefix + "/teacher";
    }

    /**
     * 查询技师列表
     */
    @RequiresPermissions("appoint:teacher:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Teacher teacher) {
        startPage();
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        return getDataTable(list);
    }

    /**
     * 导出技师列表
     */
    @RequiresPermissions("appoint:teacher:export")
    @Log(title = "技师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Teacher teacher) {
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
        return util.exportExcel(list, "技师数据");
    }

    /**
     * 新增技师
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存技师
     */
    @RequiresPermissions("appoint:teacher:add")
    @Log(title = "技师", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Teacher teacher) {
        return toAjax(teacherService.insertTeacher(teacher));
    }

    /**
     * 修改技师
     */
    @RequiresPermissions("appoint:teacher:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Teacher teacher = teacherService.selectTeacherById(id);
        mmap.put("teacher", teacher);
        return prefix + "/edit";
    }

    /**
     * 修改保存技师
     */
    @RequiresPermissions("appoint:teacher:edit")
    @Log(title = "技师", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Teacher teacher) {
        return toAjax(teacherService.updateTeacher(teacher));
    }

    /**
     * 删除技师
     */
    @RequiresPermissions("appoint:teacher:remove")
    @Log(title = "技师", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {

        String[] strs = ids.split(",");
        for (String teacherId : strs) {
            settingService.deleteSettingByTeacherid(teacherId);
        }

        return toAjax(teacherService.deleteTeacherByIds(ids));

    }
}
