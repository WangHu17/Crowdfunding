package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.po.MemberPO;
import com.example.crowdfunding.bean.po.MemberPOExample;
import com.example.crowdfunding.mapper.MemberPOMapper;
import com.example.crowdfunding.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-28 15:53
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;

    public MemberPO getMemberPOByLoginAcct(String loginAcct) {
        MemberPOExample example = new MemberPOExample();
        example.createCriteria().andLoginacctEqualTo(loginAcct);
        List<MemberPO> members = memberPOMapper.selectByExample(example);
        if (members == null || members.size() == 0) return null;
        return members.get(0);
    }

    @Override
    public void saveMemberPO(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }
}
