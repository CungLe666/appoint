package com.ruoyi.appoint.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.appoint.member.domain.Member;
import com.ruoyi.appoint.member.mapper.MemberMapper;
import com.ruoyi.appoint.member.service.IMemberService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

/**
 * 会员Service业务层处理
 * 
 * @author likang
 * @date 2023-03-12
 */
@Service
public class MemberServiceImpl implements IMemberService
{
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询会员
     * 
     * @param id
     *            会员主键
     * @return 会员
     */
    @Override
    public Member selectMemberById(Long id) {
        return memberMapper.selectMemberById(id);
    }

    /**
     * 查询会员列表
     * 
     * @param member
     *            会员
     * @return 会员
     */
    @Override
    public List<Member> selectMemberList(Member member) {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增会员
     * 
     * @param member
     *            会员
     * @return 结果
     */
    @Override
    public int insertMember(Member member) {
        member.setCreateTime(DateUtils.getNowDate());
        return memberMapper.insertMember(member);
    }

    /**
     * 修改会员
     * 
     * @param member
     *            会员
     * @return 结果
     */
    @Override
    public int updateMember(Member member) {
        return memberMapper.updateMember(member);
    }

    /**
     * 批量删除会员
     * 
     * @param ids
     *            需要删除的会员主键
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(String ids) {
        return memberMapper.deleteMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员信息
     * 
     * @param id
     *            会员主键
     * @return 结果
     */
    @Override
    public int deleteMemberById(Long id) {
        return memberMapper.deleteMemberById(id);
    }

    @Override
    public Member selectMemberByOpenId(String openid) {
        return memberMapper.selectMemberByOpenId(openid);
    }

    @Override
    public Member queryMemberByLoginName(String loginName) {
        return memberMapper.queryMemberByLoginName(loginName);
    }
}
