package com.ruoyi.web.controller.appoint.member;

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

import com.ruoyi.appoint.member.domain.Member;
import com.ruoyi.appoint.member.service.IMemberService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 会员Controller
 * 
 * @author likang
 * @date 2023-03-12
 */
@Controller
@RequestMapping("/appoint/member")
public class MemberController extends BaseController
{
    private String prefix = "appoint/member";

    @Autowired
    private IMemberService memberService;

    @RequiresPermissions("appoint:member:view")
    @GetMapping()
    public String member() {
        return prefix + "/member";
    }

    /**
     * 查询会员列表
     */
    @RequiresPermissions("appoint:member:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Member member) {
        startPage();
        List<Member> list = memberService.selectMemberList(member);
        return getDataTable(list);
    }

    /**
     * 导出会员列表
     */
    @RequiresPermissions("appoint:member:export")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Member member) {
        List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        return util.exportExcel(list, "会员数据");
    }

    /**
     * 新增会员
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存会员
     */
    @RequiresPermissions("appoint:member:add")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Member member) {
        return toAjax(memberService.insertMember(member));
    }

    /**
     * 修改会员
     */
    @RequiresPermissions("appoint:member:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Member member = memberService.selectMemberById(id);
        mmap.put("member", member);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员
     */
    @RequiresPermissions("appoint:member:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Member member) {
        return toAjax(memberService.updateMember(member));
    }

    /**
     * 删除会员
     */
    @RequiresPermissions("appoint:member:remove")
    @Log(title = "会员", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(memberService.deleteMemberByIds(ids));
    }
}
