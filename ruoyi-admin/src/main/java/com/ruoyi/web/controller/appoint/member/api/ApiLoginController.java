package com.ruoyi.web.controller.appoint.member.api;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.appoint.member.domain.Member;
import com.ruoyi.appoint.member.service.IMemberService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.JwtUtils;

/**
 * 登录注册接口
 * 
 * @author likang
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController
{

    @Autowired
    private IMemberService memberService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录
     */
    @RequestMapping(value = "/login")
    public AjaxResult login(String loginName, String password) throws Exception {

        // 用户信息
        Member member = memberService.queryMemberByLoginName(loginName);

        // 账号不存在、密码错误
        if (member == null || !member.getPassword().equals(password)) {
            return AjaxResult.error("账号或密码不正确");
        }

        // 生成token
        String token = jwtUtils.generateToken(member.getId());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("token", token);
        data.put("member", member);
        return AjaxResult.success(data);
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AjaxResult register(@RequestBody Member member) throws IOException {
        Member memberOld = memberService.queryMemberByLoginName(member.getLoginName());
        if (memberOld != null) { // 判断账号是否已注册
            return AjaxResult.error("账号已存在!");
        }
        if ("1".equals(member.getGender())) { // 设置默认头像
            member.setAvatarUrl("http://localhost:8090" + "/img/avatar.png");
        }
        if ("0".equals(member.getGender())) { // 设置默认头像
            member.setAvatarUrl("http://localhost:8090" + "/img/avatar-2.png");
        }
        member.setCreateTime(new Date());
        memberService.insertMember(member);
        return AjaxResult.success();
    }

}
