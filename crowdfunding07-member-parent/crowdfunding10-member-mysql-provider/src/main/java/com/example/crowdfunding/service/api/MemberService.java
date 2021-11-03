package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.po.MemberPO;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-28 15:53
 */
public interface MemberService {

    MemberPO getMemberPOByLoginAcct(String loginAcct);

    /**
     * 保存memberPO对象到数据库
     * @param memberPO
     */
    void saveMemberPO(MemberPO memberPO);
}
