package com.example.crowdfunding.test;

import com.example.crowdfunding.api.MysqlRemoteService;
import com.example.crowdfunding.bean.vo.OrderProjectVO;
import com.example.crowdfunding.util.ResultEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-27 19:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    @Test
    public void testGetOrderProjectVORemote() {
        ResultEntity<OrderProjectVO> orderProjectVO = mysqlRemoteService.getOrderProjectVORemote(15);
        System.out.println(orderProjectVO.toString());
    }

}
