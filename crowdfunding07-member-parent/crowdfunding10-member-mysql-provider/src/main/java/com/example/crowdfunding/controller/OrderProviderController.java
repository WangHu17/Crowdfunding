package com.example.crowdfunding.controller;

import com.example.crowdfunding.bean.po.AddressPO;
import com.example.crowdfunding.bean.vo.OrderProjectVO;
import com.example.crowdfunding.service.api.OrderService;
import com.example.crowdfunding.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.SAAJResult;
import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-18 17:23
 */
@RestController
public class OrderProviderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/save/shipping/address")
    ResultEntity<String> saveShippingAddress(@RequestBody AddressPO addressPO) {
        try {
            orderService.saveShippingAddress(addressPO);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/shipping/address/remote")
    ResultEntity<List<AddressPO>> getShippingAddressList(@RequestParam("memberId") Integer memberId) {
        try {
            List<AddressPO> addressList = orderService.getAddressList(memberId);
            return ResultEntity.successWithData(addressList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/order/projectvO/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("returnId")Integer returnId) {
        try {
            OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(returnId);
            return ResultEntity.successWithData(orderProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }


}
