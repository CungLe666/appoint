package com.ruoyi.appoint.category.mapper;

import java.util.List;

import com.ruoyi.appoint.category.domain.Category;

/**
 * 分类Mapper接口
 * 
 * @author likang
 * @date 2023-03-11
 */
public interface CategoryMapper
{
    /**
     * 查询分类
     * 
     * @param id
     *            分类主键
     * @return 分类
     */
    public Category selectCategoryById(Long id);

    /**
     * 查询分类列表
     * 
     * @param category
     *            分类
     * @return 分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增分类
     * 
     * @param category
     *            分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改分类
     * 
     * @param category
     *            分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 删除分类
     * 
     * @param id
     *            分类主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 批量删除分类
     * 
     * @param ids
     *            需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(String[] ids);

    public List<Category> selectCategoryName();

    public List<Category> queryCategoryList();
}
