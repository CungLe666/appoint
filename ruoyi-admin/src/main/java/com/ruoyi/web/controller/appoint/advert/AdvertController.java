package com.ruoyi.web.controller.appoint.advert;

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

import com.ruoyi.appoint.advert.domain.Advert;
import com.ruoyi.appoint.advert.service.IAdvertService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 广告Controller
 * 
 * @author likang
 * @date 2023-03-11
 */
@Controller
@RequestMapping("/appoint/advert")
public class AdvertController extends BaseController
{
    private String prefix = "appoint/advert";

    @Autowired
    private IAdvertService advertService;

    @RequiresPermissions("appoint:advert:view")
    @GetMapping()
    public String advert() {
        return prefix + "/advert";
    }

    /**
     * 查询广告列表
     */
    @RequiresPermissions("appoint:advert:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Advert advert) {
        startPage();
        List<Advert> list = advertService.selectAdvertList(advert);
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @RequiresPermissions("appoint:advert:export")
    @Log(title = "广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Advert advert) {
        List<Advert> list = advertService.selectAdvertList(advert);
        ExcelUtil<Advert> util = new ExcelUtil<Advert>(Advert.class);
        return util.exportExcel(list, "广告数据");
    }

    /**
     * 新增广告
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存广告
     */
    @RequiresPermissions("appoint:advert:add")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Advert advert) {
        return toAjax(advertService.insertAdvert(advert));
    }

    /**
     * 修改广告
     */
    @RequiresPermissions("appoint:advert:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Advert advert = advertService.selectAdvertById(id);
        mmap.put("advert", advert);
        return prefix + "/edit";
    }

    /**
     * 修改保存广告
     */
    @RequiresPermissions("appoint:advert:edit")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Advert advert) {
        return toAjax(advertService.updateAdvert(advert));
    }

    /**
     * 删除广告
     */
    @RequiresPermissions("appoint:advert:remove")
    @Log(title = "广告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(advertService.deleteAdvertByIds(ids));
    }
}
