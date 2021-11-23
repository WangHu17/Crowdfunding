package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.po.AddressPO;
import com.example.crowdfunding.bean.po.AddressPOExample;
import com.example.crowdfunding.bean.vo.OrderProjectVO;
import com.example.crowdfunding.mapper.AddressPOMapper;
import com.example.crowdfunding.mapper.OrderProjectPOMapper;
import com.example.crowdfunding.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-18 17:21
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;

    @Autowired
    private AddressPOMapper addressPOMapper;

    @Override
    public OrderProjectVO getOrderProjectVO(Integer returnId) {
        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    @Override
    public List<AddressPO> getAddressList(Integer memberId) {
        AddressPOExample example = new AddressPOExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        List<AddressPO> addressPOList = addressPOMapper.selectByExample(example);
        return addressPOList;
    }

    @Override
    public void saveShippingAddress(AddressPO addressPO) {
        addressPOMapper.insert(addressPO);
    }
}
