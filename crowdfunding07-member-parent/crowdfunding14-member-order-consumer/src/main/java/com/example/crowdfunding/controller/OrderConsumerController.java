package com.example.crowdfunding.controller;

import com.example.crowdfunding.api.MysqlRemoteService;
import com.example.crowdfunding.bean.po.AddressPO;
import com.example.crowdfunding.bean.vo.MemberLoginVO;
import com.example.crowdfunding.bean.vo.OrderProjectVO;
import com.example.crowdfunding.constant.CrowdConstant;
import com.example.crowdfunding.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-18 16:29
 */
@Controller
public class OrderConsumerController {

    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    @RequestMapping("/add/address")
    public String saveAddress(AddressPO addressPO, HttpSession session) {
        mysqlRemoteService.saveShippingAddress(addressPO);
        OrderProjectVO orderProject = (OrderProjectVO) session.getAttribute("orderProject");
        Integer returnCount = orderProject.getReturnCount();
        return "redirect:http://localhost/order/confirm/order/" + returnCount;
    }

    // 去订单确认页面，并将returnCount合并到session中的orderProject对象中，并将获取到的收货地址放入session中
    @RequestMapping("/confirm/order/{returnCount}")
    public String toOrderConfirmPage(@PathVariable("returnCount")Integer returnCount, HttpSession session) {

        // 1、合并returnCount到session中的orderProject对象
        OrderProjectVO orderProject = (OrderProjectVO) session.getAttribute("orderProject");
        orderProject.setReturnCount(returnCount);
        session.setAttribute("orderProject",orderProject);

        // 2、获取收获地址
        // 先获取用户id
        MemberLoginVO member = (MemberLoginVO) session.getAttribute(CrowdConstant.MEMBER_LOGIN_ACCOUNT);
        Integer memberId = member.getId();
        ResultEntity<List<AddressPO>> resultEntity = mysqlRemoteService.getShippingAddressList(memberId);
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){
            List<AddressPO> addressList = resultEntity.getData();
            session.setAttribute("addressList",addressList);
        }
        return "order-confirm2";
    }

    // 去订单确认回报内容页面，并将查询到的回报信息放入session中
    @RequestMapping("/confirm/return/{returnId}")
    public String toReturnConfirmPage(@PathVariable("returnId")Integer returnId, HttpSession session){
        ResultEntity<OrderProjectVO> resultEntity = mysqlRemoteService.getOrderProjectVORemote(returnId);
        String result = resultEntity.getResult();
        if (ResultEntity.SUCCESS.equals(result)){
            session.setAttribute("orderProject",resultEntity.getData());
        }
        return "order-confirm";
    }

}
