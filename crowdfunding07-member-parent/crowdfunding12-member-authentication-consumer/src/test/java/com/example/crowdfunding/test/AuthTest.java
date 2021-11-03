package com.example.crowdfunding.test;


import com.example.crowdfunding.api.RedisRemoteService;
import com.example.crowdfunding.util.CrowdUtil;
import com.example.crowdfunding.util.Msg;
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
public class AuthTest {

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Test
    public void testShortMessage(){
        String host = "https://cxwg.market.alicloudapi.com";
        String path = "/sendSms";
        String method = "POST";
        String appcode = "693ec7493c5d47e6a07ad6196ab54c3e";
        String phoneNumber = "17378693948";
        String sign = "王虎";
        Msg msg = CrowdUtil.sendShortMessage(host, path, method, appcode, phoneNumber, sign, "");
        System.out.println(msg);
    }

    @Test
    public void testRedis(){
        Msg msg = redisRemoteService.getRedisStringValueByKeyRemote("wawa");
        System.out.println(msg);
    }

}
