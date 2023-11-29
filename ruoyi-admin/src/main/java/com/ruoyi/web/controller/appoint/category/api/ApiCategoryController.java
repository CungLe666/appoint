package com.ruoyi.web.controller.appoint.category.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.category.domain.Category;
import com.ruoyi.appoint.category.service.ICategoryService;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/api/category")
public class ApiCategoryController
{

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("list")
    public AjaxResult list() {
        List<Category> categoryList = categoryService.queryCategoryList();
        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", categoryList);
        return AjaxResult.success(data);
    }
}
