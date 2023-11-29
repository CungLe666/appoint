package com.ruoyi.appoint.advert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.appoint.advert.domain.Advert;
import com.ruoyi.appoint.advert.mapper.AdvertMapper;
import com.ruoyi.appoint.advert.service.IAdvertService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

/**
 * 广告Service业务层处理
 * 
 * @author likang
 * @date 2023-03-11
 */
@Service
public class AdvertServiceImpl implements IAdvertService
{
    @Autowired
    private AdvertMapper advertMapper;

    /**
     * 查询广告
     * 
     * @param id
     *            广告主键
     * @return 广告
     */
    @Override
    public Advert selectAdvertById(Long id) {
        return advertMapper.selectAdvertById(id);
    }

    /**
     * 查询广告列表
     * 
     * @param advert
     *            广告
     * @return 广告
     */
    @Override
    public List<Advert> selectAdvertList(Advert advert) {
        return advertMapper.selectAdvertList(advert);
    }

    /**
     * 新增广告
     * 
     * @param advert
     *            广告
     * @return 结果
     */
    @Override
    public int insertAdvert(Advert advert) {
        advert.setCreateTime(DateUtils.getNowDate());
        return advertMapper.insertAdvert(advert);
    }

    /**
     * 修改广告
     * 
     * @param advert
     *            广告
     * @return 结果
     */
    @Override
    public int updateAdvert(Advert advert) {
        return advertMapper.updateAdvert(advert);
    }

    /**
     * 批量删除广告
     * 
     * @param ids
     *            需要删除的广告主键
     * @return 结果
     */
    @Override
    public int deleteAdvertByIds(String ids) {
        return advertMapper.deleteAdvertByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除广告信息
     * 
     * @param id
     *            广告主键
     * @return 结果
     */
    @Override
    public int deleteAdvertById(Long id) {
        return advertMapper.deleteAdvertById(id);
    }

    @Override
    public List<Advert> quaryAdvertList() {
        return advertMapper.quaryAdvertList();
    }
}
