package com.ruoyi.web.controller.appoint.member.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.member.domain.Member;
import com.ruoyi.appoint.member.service.IMemberService;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 微信用户接口
 * 
 * @author likang
 * @version 2023年3月12日
 */
@RestController
@RequestMapping("/api/member")
public class ApiMemberController
{

    @Autowired
    private IMemberService memberService;

    /**
     * 用户详情
     * 
     * @param userId
     * @return
     */
    @GetMapping("/info")
    public AjaxResult info(String userId) {
        Member member = memberService.selectMemberById(Long.valueOf(userId));
        Map<String, Object> data = new HashMap<>();
        data.put("member", member);
        return AjaxResult.success(data);
    }

    /**
     * 更新用户
     * 
     * @param member
     * @return
     */
    @RequestMapping("/update")
    public AjaxResult update(@RequestBody Member member) {
        memberService.updateMember(member);
        return AjaxResult.success();
    }
}
