package com.ruoyi.web.controller.appoint.category;

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

import com.ruoyi.appoint.category.domain.Category;
import com.ruoyi.appoint.category.service.ICategoryService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 分类Controller
 * 
 * @author likang
 * @date 2023-03-11
 */
@Controller
@RequestMapping("/appoint/category")
public class CategoryController extends BaseController
{
    private String prefix = "appoint/category";

    @Autowired
    private ICategoryService categoryService;

    @RequiresPermissions("appoint:category:view")
    @GetMapping()
    public String category() {
        return prefix + "/category";
    }

    /**
     * 查询分类列表
     */
    @RequiresPermissions("appoint:category:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Category category) {
        startPage();
        List<Category> categoryList = categoryService.selectCategoryList(category);
        return getDataTable(categoryList);
    }

    /**
     * 导出分类列表
     */
    @RequiresPermissions("appoint:category:export")
    @Log(title = "分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Category category) {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        return util.exportExcel(list, "分类数据");
    }

    /**
     * 新增分类
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存分类
     */
    @RequiresPermissions("appoint:category:add")
    @Log(title = "分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Category category) {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改分类
     */
    @RequiresPermissions("appoint:category:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Category category = categoryService.selectCategoryById(id);
        mmap.put("category", category);
        return prefix + "/edit";
    }

    /**
     * 修改保存分类
     */
    @RequiresPermissions("appoint:category:edit")
    @Log(title = "分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Category category) {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除分类
     */
    @RequiresPermissions("appoint:category:remove")
    @Log(title = "分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {

        return toAjax(categoryService.deleteCategoryByIds(ids));
    }
}
