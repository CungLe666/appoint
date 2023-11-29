package com.ruoyi.appoint.setting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.appoint.setting.domain.Setting;
import com.ruoyi.appoint.setting.mapper.SettingMapper;
import com.ruoyi.appoint.setting.service.ISettingService;
import com.ruoyi.common.core.text.Convert;

/**
 * 预约设置Service业务层处理
 * 
 * @author likang
 * @date 2023-03-11
 */
@Service
public class SettingServiceImpl implements ISettingService
{
    @Autowired
    private SettingMapper settingMapper;

    /**
     * 查询预约设置
     * 
     * @param id
     *            预约设置主键
     * @return 预约设置
     */
    @Override
    public Setting selectSettingById(Long id) {
        return settingMapper.selectSettingById(id);
    }

    /**
     * 查询预约设置列表
     * 
     * @param setting
     *            预约设置
     * @return 预约设置
     */
    @Override
    public List<Setting> selectSettingList(Setting setting) {
        return settingMapper.selectSettingList(setting);
    }

    /**
     * 新增预约设置
     * 
     * @param setting
     *            预约设置
     * @return 结果
     */
    @Override
    public int insertSetting(Setting setting) {
        return settingMapper.insertSetting(setting);
    }

    /**
     * 修改预约设置
     * 
     * @param setting
     *            预约设置
     * @return 结果
     */
    @Override
    public int updateSetting(Setting setting) {
        return settingMapper.updateSetting(setting);
    }

    /**
     * 批量删除预约设置
     * 
     * @param ids
     *            需要删除的预约设置主键
     * @return 结果
     */
    @Override
    public int deleteSettingByIds(String ids) {
        return settingMapper.deleteSettingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除预约设置信息
     * 
     * @param id
     *            预约设置主键
     * @return 结果
     */
    @Override
    public int deleteSettingById(Long id) {
        return settingMapper.deleteSettingById(id);
    }

    @Override
    public List<Setting> querySettingListByTeacherId(String teacherId) {
        return settingMapper.querySettingListByTeacherId(teacherId);
    }

    @Override
    public int deleteSettingByTeacherid(String teacherId) {
        return settingMapper.deleteSettingByTeacherid(teacherId);

    }
}
