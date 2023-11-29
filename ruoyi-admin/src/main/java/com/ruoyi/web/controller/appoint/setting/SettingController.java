package com.ruoyi.web.controller.appoint.setting;

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

import com.ruoyi.appoint.setting.domain.Setting;
import com.ruoyi.appoint.setting.service.ISettingService;
import com.ruoyi.appoint.teacher.service.ITeacherService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 预约设置Controller
 * 
 * @author likang
 * @date 2023-03-11
 */
@Controller
@RequestMapping("/appoint/setting")
public class SettingController extends BaseController
{
    private String prefix = "appoint/setting";

    @Autowired
    private ISettingService settingService;

    @Autowired
    private ITeacherService teacherService;

    @RequiresPermissions("appoint:setting:view")
    @GetMapping()
    public String setting() {
        return prefix + "/setting";
    }

    /**
     * 查询预约设置列表
     */
    @RequiresPermissions("appoint:setting:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Setting setting) {
        startPage();
        List<Setting> list = settingService.selectSettingList(setting);

        for (Setting setting2 : list) {
            setting2.setTeacherName(teacherService.selectTeacherById(setting2.getTeacherId()).getRealName());
        }

        return getDataTable(list);
    }

    /**
     * 导出预约设置列表
     */
    @RequiresPermissions("appoint:setting:export")
    @Log(title = "预约设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Setting setting) {
        List<Setting> list = settingService.selectSettingList(setting);
        ExcelUtil<Setting> util = new ExcelUtil<Setting>(Setting.class);
        return util.exportExcel(list, "预约设置数据");
    }

    /**
     * 新增预约设置
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("teacherList", teacherService.selectTeacherName());
        return prefix + "/add";
    }

    /**
     * 新增保存预约设置
     */
    @RequiresPermissions("appoint:setting:add")
    @Log(title = "预约设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Setting setting) {
        return toAjax(settingService.insertSetting(setting));
    }

    /**
     * 修改预约设置
     */
    @RequiresPermissions("appoint:setting:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Setting setting = settingService.selectSettingById(id);
        mmap.put("setting", setting);
        mmap.put("teacherList", teacherService.selectTeacherName());
        return prefix + "/edit";
    }

    /**
     * 修改保存预约设置
     */
    @RequiresPermissions("appoint:setting:edit")
    @Log(title = "预约设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Setting setting) {
        return toAjax(settingService.updateSetting(setting));
    }

    /**
     * 删除预约设置
     */
    @RequiresPermissions("appoint:setting:remove")
    @Log(title = "预约设置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(settingService.deleteSettingByIds(ids));
    }
}
