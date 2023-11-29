package com.ruoyi.web.controller.appoint.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.appoint.member.domain.Member;
import com.ruoyi.appoint.member.service.IMemberService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.JwtUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;

/**
 * 微信授权登录
 * 
 * @author likang
 * @version 2023年3月12日
 */
@RestController
@RequestMapping("/api/wechat")
public class WxMaUserController
{

    @Autowired
    IMemberService memberService;

    @Autowired
    private JwtUtils jwtUtils;
    /**
     * 微信小程序APPID
     */
    private final static String AppID = "wx471f2d2e585852f4";
    /**
     * 微信APP密钥
     */
    private final static String AppSecret = "44efd19c8367f95f287d4a4bec51415a";

    /**
     * 登录时获取的 code（微信官方提供的临时凭证）
     * 
     * @param object
     * @return
     */
    @PostMapping("/login")
    public AjaxResult wxLogin(@RequestBody JSONObject object) {
        // 微信官方提供的微信小程序登录授权时使用的URL地址
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        /**
         * 拼接需要的参数
         * appid = AppID 你自己的微信小程序APPID
         * js_code = AppSecret 你自己的微信APP密钥
         * grant_type=authorization_code = code 微信官方提供的临时凭证
         */
        String params = StringUtils.format("appid={}&secret={}&js_code={}&grant_type=authorization_code", AppID,
                AppSecret, object.get("code"));
        // 开始发起网络请求,若依管理系统自带网络请求工具，直接使用即可
        String res = HttpUtils.sendGet(url, params);
        JSONObject jsonObject = JSON.parseObject(res);
        // String session_key = (String) jsonObject.get("session_key");
        String openid = (String) jsonObject.get("openid");
        if (StringUtils.isEmpty(openid)) {
            return AjaxResult.error("未获取到");
        }

        Member member = null;

        // 先通过openid来查询是否存在
        member = memberService.selectMemberByOpenId(openid);
        if (member == null) {
            // 如果不存在就插入到我们的数据库里
            member = new Member();
            member.setNickname(String.valueOf(object.get("nickName")));
            member.setAvatarUrl(String.valueOf(object.get("avatarUrl")));
            member.setGender(String.valueOf(object.get("gender")));
            member.setOpenId(openid);
            member.setCreateTime(new Date());

            memberService.insertMember(member);
        }
        else {
            // 如果存在就更新数据库里原有的数据
            member.setNickname(String.valueOf(object.get("nickName")));
            member.setAvatarUrl(String.valueOf(object.get("avatarUrl")));
            // member.setGender(String.valueOf(object.get("gender")));

            memberService.updateMember(member);
        }
        // 返回结果集到前段
        Map<String, Object> data = new HashMap<>();
        String token = null;
        try {
            token = jwtUtils.generateToken(member.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // String token = UUID.randomUUID().toString();
        data.put("token", token);
        data.put("userInfo", member);

        return AjaxResult.success(data);
    }
}
