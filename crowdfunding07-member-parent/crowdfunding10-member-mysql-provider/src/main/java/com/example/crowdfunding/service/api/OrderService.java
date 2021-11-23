package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.po.AddressPO;
import com.example.crowdfunding.bean.vo.OrderProjectVO;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-18 17:21
 */
public interface OrderService {

    OrderProjectVO getOrderProjectVO(Integer returnId);

    List<AddressPO> getAddressList(Integer memberId);

    void saveShippingAddress(AddressPO addressPO);
}
