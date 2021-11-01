package com.example.crowdfunding.controller;

import com.example.crowdfunding.bean.po.MemberPO;
import com.example.crowdfunding.service.api.MemberService;
import com.example.crowdfunding.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-28 15:59
 */
@RestController
public class MemberProviderController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/get/memberpo/by/loginacct/remote")
    public Msg getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct){
        try {
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginAcct);
            return Msg.success().add("memberPO",memberPO);
        } catch (Exception e) {
            return Msg.failWithMsg(e.getMessage());
        }
    }

}
