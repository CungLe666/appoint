package com.ruoyi.web.controller.appoint.evaluation.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.evaluation.domain.Evaluation;
import com.ruoyi.appoint.evaluation.service.IEvaluationService;
import com.ruoyi.appoint.order.domain.Order;
import com.ruoyi.appoint.order.service.IOrderService;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 评价留言
 * 
 * @author likang
 * @version 2023年3月13日
 */
@RestController
@RequestMapping("/api/order/evaluation")
public class ApiEvaluationController
{

    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("list")
    public AjaxResult list(@RequestParam Map<String, Object> map) {
        List<Evaluation> evaluations = evaluationService.queryEvaluationList(map);
        Map<String, Object> data = new HashMap<>();
        data.put("evaluations", evaluations);
        return AjaxResult.success(data);
    }

    @GetMapping("save")
    public AjaxResult save(@RequestParam Map<String, Object> map) {
        Evaluation evaluation = new Evaluation();
        evaluation.setOrderId((Long.valueOf((String) map.get("orderId"))));
        evaluation.setMemberId((Long.valueOf((String) map.get("userId"))));
        evaluation.setContent((String) map.get("content"));
        evaluation
                .setProjectId(orderService.selectOrderById((Long.valueOf((String) map.get("orderId")))).getProjectId());
        evaluation.setCreateTime(new Date());

        evaluationService.insertEvaluation(evaluation);

        Order order = orderService.selectOrderById((Long.valueOf((String) map.get("orderId"))));
        // 更新订单状态为 “已评价”
        order.setStatus(4);
        orderService.updateOrder(order);

        return AjaxResult.success();
    }
}
