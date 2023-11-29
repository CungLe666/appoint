package com.ruoyi.appoint.advert.service;

import java.util.List;

import com.ruoyi.appoint.advert.domain.Advert;

/**
 * 广告Service接口
 * 
 * @author likang
 * @date 2023-03-11
 */
public interface IAdvertService
{
    /**
     * 查询广告
     * 
     * @param id
     *            广告主键
     * @return 广告
     */
    public Advert selectAdvertById(Long id);

    /**
     * 查询广告列表
     * 
     * @param advert
     *            广告
     * @return 广告集合
     */
    public List<Advert> selectAdvertList(Advert advert);

    /**
     * 新增广告
     * 
     * @param advert
     *            广告
     * @return 结果
     */
    public int insertAdvert(Advert advert);

    /**
     * 修改广告
     * 
     * @param advert
     *            广告
     * @return 结果
     */
    public int updateAdvert(Advert advert);

    /**
     * 批量删除广告
     * 
     * @param ids
     *            需要删除的广告主键集合
     * @return 结果
     */
    public int deleteAdvertByIds(String ids);

    /**
     * 删除广告信息
     * 
     * @param id
     *            广告主键
     * @return 结果
     */
    public int deleteAdvertById(Long id);

    public List<Advert> quaryAdvertList();
}
