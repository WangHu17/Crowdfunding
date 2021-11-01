package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.po.MemberPO;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-28 15:53
 */
public interface MemberService {

    public MemberPO getMemberPOByLoginAcct(String loginAcct);

}
