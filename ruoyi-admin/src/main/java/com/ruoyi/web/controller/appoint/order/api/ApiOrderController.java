package com.ruoyi.web.controller.appoint.order.api;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.order.domain.Order;
import com.ruoyi.appoint.order.service.IOrderService;
import com.ruoyi.appoint.setting.domain.Setting;
import com.ruoyi.appoint.setting.service.ISettingService;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/api/order/")
public class ApiOrderController
{

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ISettingService settingService;

    @GetMapping("list")
    public AjaxResult list(@RequestParam Map<String, Object> params) {
        List<Order> orderList = orderService.queryOrderListByUserIdAndStatus(params);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("orderList", orderList);
        return AjaxResult.success(data);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public AjaxResult save(@RequestParam Map<String, Object> params) {
        Order order = new Order();

        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String) params.get("appointTime"));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        BigDecimal totalAmount = new BigDecimal((String) params.get("totalAmount"));

        order.setOrderNumber(getOrderNumber());
        order.setProjectId(Long.valueOf((String) params.get("projectId")));
        order.setUserId(Long.valueOf((String) params.get("userId")));
        order.setTeacherId(Long.valueOf((String) params.get("teacherId")));
        order.setTeacherName((String) params.get("teacherName"));
        order.setProjectName((String) params.get("projectName"));
        order.setPicUrl((String) params.get("picUrl"));
        order.setAppointTime(date);
        order.setStatus(1);
        order.setTotalAmount(totalAmount);
        order.setTimeId(Long.valueOf((String) params.get("timeId")));
        order.setTime((String) params.get("time"));
        order.setCreateTime(new Date());

        // 插入预约订单数据
        orderService.insertOrder(order);

        // 更新时间表的预约最大人数-1
        Setting setting = settingService.selectSettingById(order.getTimeId());
        setting.setMaxPeople(setting.getMaxPeople() - 1);
        settingService.updateSetting(setting);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", order.getId());
        return AjaxResult.success(data);
    }

    @GetMapping("detail")
    public AjaxResult detail(String id) {
        Order order = orderService.selectOrderById(Long.valueOf(id));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("order", order);
        return AjaxResult.success(data);
    }

    @GetMapping("pay")
    public AjaxResult pay(String id) {
        Order order = new Order();
        order.setId(Long.valueOf(id));
        order.setStatus(2);
        orderService.updateOrder(order);
        return AjaxResult.success();
    }

    @GetMapping("cancel")
    public AjaxResult cancel(String id) {
        Order order = new Order();
        order.setId(Long.valueOf(id));
        order.setStatus(0);
        orderService.updateOrder(order);

        // 更新时间表的预约最大人数+1
        Setting setting = settingService.selectSettingById(orderService.selectOrderById(Long.valueOf(id)).getTimeId());
        setting.setMaxPeople(setting.getMaxPeople() + 1);
        settingService.updateSetting(setting);

        return AjaxResult.success();
    }

    private String getOrderNumber() {
        String randomNum = String.valueOf((1 + new Random().nextDouble()) * Math.pow(10, 6));
        String rm = randomNum.substring(1, 7);
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) + rm;
    }
}
