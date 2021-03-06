package com.example.crowdfunding.api;

import com.example.crowdfunding.bean.po.AddressPO;
import com.example.crowdfunding.bean.po.MemberPO;
import com.example.crowdfunding.bean.vo.OrderProjectVO;
import com.example.crowdfunding.bean.vo.PortalTypeVO;
import com.example.crowdfunding.bean.vo.ProjectDetailVO;
import com.example.crowdfunding.bean.vo.ProjectVO;
import com.example.crowdfunding.util.Msg;
import com.example.crowdfunding.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @RequestMapping("/save/projectvo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId")Integer memberId);

    @RequestMapping("/get/portal/type/project/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectRemote();

    @RequestMapping("/get/project/detail/{id}")
    ResultEntity<ProjectDetailVO> getProjectDetailVORemote(@PathVariable("id")Integer projectId);

    @RequestMapping("/get/order/projectvO/remote")
    ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("returnId")Integer returnId);

    @RequestMapping("/get/shipping/address/remote")
    ResultEntity<List<AddressPO>> getShippingAddressList(@RequestParam("memberId") Integer memberId);

    @RequestMapping("/save/shipping/address")
    ResultEntity<String> saveShippingAddress(@RequestBody AddressPO addressPO);
}
