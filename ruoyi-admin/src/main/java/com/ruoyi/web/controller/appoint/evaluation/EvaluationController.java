package com.ruoyi.web.controller.appoint.evaluation;

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

import com.ruoyi.appoint.evaluation.domain.Evaluation;
import com.ruoyi.appoint.evaluation.service.IEvaluationService;
import com.ruoyi.appoint.member.service.IMemberService;
import com.ruoyi.appoint.order.service.IOrderService;
import com.ruoyi.appoint.project.service.IProjectService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 预约评价Controller
 * 
 * @author likang
 * @date 2023-03-11
 */
@Controller
@RequestMapping("/appoint/evaluation")
public class EvaluationController extends BaseController
{
    private String prefix = "appoint/evaluation";

    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IOrderService orderService;

    @RequiresPermissions("appoint:evaluation:view")
    @GetMapping()
    public String evaluation() {
        return prefix + "/evaluation";
    }

    /**
     * 查询预约评价列表
     */
    @RequiresPermissions("appoint:evaluation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Evaluation evaluation) {
        startPage();
        List<Evaluation> list = evaluationService.selectEvaluationList(evaluation);

        for (Evaluation evaluation2 : list) {
            evaluation2.setProjectName(projectService.selectProjectById(evaluation2.getProjectId()).getProjectName());
            evaluation2.setTeacherName(orderService.selectOrderById(evaluation2.getOrderId()).getTeacherName());
            evaluation2.setUserName(memberService.selectMemberById(evaluation2.getMemberId()).getRealName());
        }

        return getDataTable(list);
    }

    /**
     * 导出预约评价列表
     */
    @RequiresPermissions("appoint:evaluation:export")
    @Log(title = "预约评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Evaluation evaluation) {
        List<Evaluation> list = evaluationService.selectEvaluationList(evaluation);
        ExcelUtil<Evaluation> util = new ExcelUtil<Evaluation>(Evaluation.class);
        return util.exportExcel(list, "预约评价数据");
    }

    /**
     * 新增预约评价
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存预约评价
     */
    @RequiresPermissions("appoint:evaluation:add")
    @Log(title = "预约评价", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Evaluation evaluation) {
        return toAjax(evaluationService.insertEvaluation(evaluation));
    }

    /**
     * 修改预约评价
     */
    @RequiresPermissions("appoint:evaluation:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Evaluation evaluation = evaluationService.selectEvaluationById(id);
        mmap.put("evaluation", evaluation);
        return prefix + "/edit";
    }

    /**
     * 修改保存预约评价
     */
    @RequiresPermissions("appoint:evaluation:edit")
    @Log(title = "预约评价", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Evaluation evaluation) {
        return toAjax(evaluationService.updateEvaluation(evaluation));
    }

    /**
     * 删除预约评价
     */
    @RequiresPermissions("appoint:evaluation:remove")
    @Log(title = "预约评价", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(evaluationService.deleteEvaluationByIds(ids));
    }
}
