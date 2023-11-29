package com.ruoyi.web.controller.appoint.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.appoint.member.service.IMemberService;
import com.ruoyi.appoint.order.domain.Order;
import com.ruoyi.appoint.order.service.IOrderService;
import com.ruoyi.appoint.setting.domain.Setting;
import com.ruoyi.appoint.setting.service.ISettingService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 预约Controller
 * 
 * @author likang
 * @date 2023-03-11
 */
@Controller
@RequestMapping("/appoint/order")
public class OrderController extends BaseController
{
    private String prefix = "appoint/order";

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ISettingService settingService;

    @RequiresPermissions("appoint:order:view")
    @GetMapping()
    public String order() {
        return prefix + "/order";
    }

    /**
     * 查询预约列表
     */
    @RequiresPermissions("appoint:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Order order) {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        for (Order order2 : list) {
            order2.setUserName(memberService.selectMemberById(order2.getUserId()).getRealName());
        }

        return getDataTable(list);
    }

    /**
     * 导出预约列表
     */
    @RequiresPermissions("appoint:order:export")
    @Log(title = "预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Order order) {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        return util.exportExcel(list, "预约数据");
    }

    /**
     * 新增预约
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存预约
     */
    @RequiresPermissions("appoint:order:add")
    @Log(title = "预约", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Order order) {
        return toAjax(orderService.insertOrder(order));
    }

    /**
     * 修改预约
     */
    @RequiresPermissions("appoint:order:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Order order = orderService.selectOrderById(id);
        mmap.put("order", order);
        return prefix + "/edit";
    }

    /**
     * 删除预约
     */
    @RequiresPermissions("appoint:order:remove")
    @Log(title = "预约", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(orderService.deleteOrderByIds(ids));
    }

    /**
     * 
     * 完成预约
     * 
     * @param ids
     * @return
     */
    @RequiresPermissions("appoint:order:complete")
    @Log(title = "预约", businessType = BusinessType.UPDATE)
    @PostMapping("/complete")
    @ResponseBody
    public AjaxResult complete(String ids) {
        String[] orderIds = ids.split(",");
        List<String> idList = new ArrayList<>();
        for (String orderId : orderIds) {
            Order order = orderService.selectOrderById(Long.valueOf(orderId));
            if (order.getStatus() == 2) {
                idList.add(orderId);
            }
        }
        // 更新时间表的预约最大人数+1
        for (String id : idList) {
            Setting setting = settingService
                    .selectSettingById(orderService.selectOrderById(Long.valueOf(id)).getTimeId());
            setting.setMaxPeople(setting.getMaxPeople() + 1);
            settingService.updateSetting(setting);
        }
        String newIds = String.join(",", idList);
        if (newIds == null || newIds.length() == 0) {
            return AjaxResult.error("请选择预约中的订单");
        }
        else {
            return toAjax(orderService.completeOrderByIds(newIds));
        }
    }
}
