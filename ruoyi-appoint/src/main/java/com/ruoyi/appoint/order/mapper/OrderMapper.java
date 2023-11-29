package com.ruoyi.appoint.order.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.appoint.order.domain.Order;

/**
 * 预约Mapper接口
 * 
 * @author likang
 * @date 2023-03-11
 */
public interface OrderMapper
{
    /**
     * 查询预约
     * 
     * @param id
     *            预约主键
     * @return 预约
     */
    public Order selectOrderById(Long id);

    /**
     * 查询预约列表
     * 
     * @param order
     *            预约
     * @return 预约集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增预约
     * 
     * @param order
     *            预约
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改预约
     * 
     * @param order
     *            预约
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 删除预约
     * 
     * @param id
     *            预约主键
     * @return 结果
     */
    public int deleteOrderById(Long id);

    /**
     * 批量删除预约
     * 
     * @param ids
     *            需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderByIds(String[] ids);

    public List<Order> queryOrderListByUserIdAndStatus(Map<String, Object> params);

    public int completeOrderByIds(String[] strArray);
}
