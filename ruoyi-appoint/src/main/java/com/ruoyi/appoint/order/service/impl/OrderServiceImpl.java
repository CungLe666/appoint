package com.ruoyi.appoint.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.appoint.order.domain.Order;
import com.ruoyi.appoint.order.mapper.OrderMapper;
import com.ruoyi.appoint.order.service.IOrderService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

/**
 * 预约Service业务层处理
 * 
 * @author likang
 * @date 2023-03-11
 */
@Service
public class OrderServiceImpl implements IOrderService
{
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询预约
     * 
     * @param id
     *            预约主键
     * @return 预约
     */
    @Override
    public Order selectOrderById(Long id) {
        return orderMapper.selectOrderById(id);
    }

    /**
     * 查询预约列表
     * 
     * @param order
     *            预约
     * @return 预约
     */
    @Override
    public List<Order> selectOrderList(Order order) {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增预约
     * 
     * @param order
     *            预约
     * @return 结果
     */
    @Override
    public int insertOrder(Order order) {
        order.setCreateTime(DateUtils.getNowDate());
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改预约
     * 
     * @param order
     *            预约
     * @return 结果
     */
    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除预约
     * 
     * @param ids
     *            需要删除的预约主键
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(String ids) {
        return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除预约信息
     * 
     * @param id
     *            预约主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id) {
        return orderMapper.deleteOrderById(id);
    }

    @Override
    public List<Order> queryOrderListByUserIdAndStatus(Map<String, Object> params) {
        return orderMapper.queryOrderListByUserIdAndStatus(params);
    }

    @Override
    public int completeOrderByIds(String ids) {
        return orderMapper.completeOrderByIds(Convert.toStrArray(ids));
    }
}
