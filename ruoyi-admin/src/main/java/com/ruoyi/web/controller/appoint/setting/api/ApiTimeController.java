package com.ruoyi.web.controller.appoint.setting.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.setting.domain.Setting;
import com.ruoyi.appoint.setting.service.ISettingService;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/api/time")
public class ApiTimeController
{

    @Autowired
    private ISettingService settingService;

    @GetMapping("list")
    public AjaxResult list(String teacherId) {
        List<Setting> timeList = settingService.querySettingListByTeacherId(teacherId);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("timeList", timeList);
        return AjaxResult.success(data);
    }
}
