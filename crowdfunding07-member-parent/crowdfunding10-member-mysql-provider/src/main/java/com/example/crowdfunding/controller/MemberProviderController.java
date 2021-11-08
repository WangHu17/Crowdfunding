package com.example.crowdfunding.controller;

import com.example.crowdfunding.bean.po.MemberPO;
import com.example.crowdfunding.service.api.MemberService;
import com.example.crowdfunding.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
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

    // 通过用户名获取memberPO对象
    @RequestMapping("/get/memberpo/by/loginacct/remote")
    public Msg getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct){
        try {
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginAcct);
            return Msg.success().add("memberPO",memberPO);
        } catch (Exception e) {
            return Msg.failWithMsg(e.getMessage());
        }
    }

    // 保存memberPO对象到数据库
    @RequestMapping("/save/memberpo/remote")
    public Msg saveMemberPO(@RequestBody MemberPO memberPO){
        try {
            memberService.saveMemberPO(memberPO);
            return Msg.successWithMsg("注册成功");
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                return Msg.failWithMsg("用户名已存在");
            }
            return Msg.failWithMsg(e.getMessage());
        }
    }

}
