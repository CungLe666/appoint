package com.ruoyi.web.controller.appoint.advert.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.advert.domain.Advert;
import com.ruoyi.appoint.advert.service.IAdvertService;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/api/advert/")
public class ApiAdvertController
{

    @Autowired
    private IAdvertService advertService;

    @GetMapping("list")
    public AjaxResult list() {
        List<Advert> advertList = advertService.quaryAdvertList();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("advertList", advertList);
        return AjaxResult.success(data);
    }
}
