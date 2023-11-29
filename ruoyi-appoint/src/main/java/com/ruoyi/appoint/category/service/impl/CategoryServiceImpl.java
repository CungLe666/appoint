package com.ruoyi.appoint.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.appoint.category.domain.Category;
import com.ruoyi.appoint.category.mapper.CategoryMapper;
import com.ruoyi.appoint.category.service.ICategoryService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

/**
 * 分类Service业务层处理
 * 
 * @author likang
 * @date 2023-03-11
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询分类
     * 
     * @param id
     *            分类主键
     * @return 分类
     */
    @Override
    public Category selectCategoryById(Long id) {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询分类列表
     * 
     * @param category
     *            分类
     * @return 分类
     */
    @Override
    public List<Category> selectCategoryList(Category category) {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增分类
     * 
     * @param category
     *            分类
     * @return 结果
     */
    @Override
    public int insertCategory(Category category) {
        category.setCreateTime(DateUtils.getNowDate());
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改分类
     * 
     * @param category
     *            分类
     * @return 结果
     */
    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除分类
     * 
     * @param ids
     *            需要删除的分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(String ids) {
        return categoryMapper.deleteCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除分类信息
     * 
     * @param id
     *            分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id) {
        return categoryMapper.deleteCategoryById(id);
    }

    @Override
    public List<Category> selectCategoryName() {
        return categoryMapper.selectCategoryName();
    }

    @Override
    public List<Category> queryCategoryList() {
        return categoryMapper.queryCategoryList();
    }
}
