package com.ruoyi.appoint.setting.mapper;

import java.util.List;

import com.ruoyi.appoint.setting.domain.Setting;
import org.apache.ibatis.annotations.Param;

/**
 * 预约设置Mapper接口
 * 
 * @author likang
 * @date 2023-03-11
 */
public interface SettingMapper
{
    /**
     * 查询预约设置
     * 
     * @param id
     *            预约设置主键
     * @return 预约设置
     */
    public Setting selectSettingById(Long id);

    /**
     * 查询预约设置列表
     * 
     * @param setting
     *            预约设置
     * @return 预约设置集合
     */
    public List<Setting> selectSettingList(Setting setting);

    /**
     * 新增预约设置
     * 
     * @param setting
     *            预约设置
     * @return 结果
     */
    public int insertSetting(Setting setting);

    /**
     * 修改预约设置
     * 
     * @param setting
     *            预约设置
     * @return 结果
     */
    public int updateSetting(Setting setting);

    /**
     * 删除预约设置
     * 
     * @param id
     *            预约设置主键
     * @return 结果
     */
    public int deleteSettingById(Long id);

    /**
     * 批量删除预约设置
     * 
     * @param ids
     *            需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSettingByIds(String[] ids);

    public List<Setting> querySettingListByTeacherId(@Param("teacherId")  String teacherId);

    public int deleteSettingByTeacherid(String teacherId);
}
