package com.example.crowdfunding.api;

import com.example.crowdfunding.bean.po.MemberPO;
import com.example.crowdfunding.util.Msg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-28 15:41
 */
@FeignClient("wh-crowd-mysql")
public interface MysqlRemoteService {

    @RequestMapping("/get/memberpo/by/loginacct/remote")
    Msg getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct);

    @RequestMapping("/save/memberpo/remote")
    Msg saveMemberPO(@RequestBody MemberPO memberPO);

}
