package com.example.crowdfunding.controller;

import com.example.crowdfunding.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-29 16:06
 */
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 设置不带过期时间的字符串数据
     */
    @RequestMapping("/set/redis/key/value/remote")
    Msg setRedisKeyValueRemote(@RequestParam("key") String key,
                               @RequestParam("value") String value) {
        try {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            ops.set(key,value);
            return Msg.success();
        } catch (Exception e) {
            return Msg.failWithMsg(e.getMessage());
        }
    }

    /**
     * 设置带过期时间的字符串数据
     */
    @RequestMapping("/set/redis/key/value/with/timeout/remote")
    Msg setRedisKeyValueRemoteWithTimeout(@RequestParam("key") String key,
                                          @RequestParam("value") String value,
                                          @RequestParam("time") long time,
                                          @RequestParam("timeUnit") TimeUnit timeUnit) {
        try {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            ops.set(key,value,time,timeUnit);
            return Msg.success();
        } catch (Exception e) {
            return Msg.failWithMsg(e.getMessage());
        }
    }

    /**
     * 获取数据
     */
    @RequestMapping("/get/redis/string/value/by/key/remote")
    Msg getRedisStringValueByKeyRemote(@RequestParam("key") String key) {
        try {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            String value = ops.get(key);
            return Msg.success().add(key,value);
        } catch (Exception e) {
            return Msg.failWithMsg(e.getMessage());
        }
    }

    /**
     * 删除数据
     */
    @RequestMapping("/remove/redis/key/remote")
    Msg removeRedisKeyRemote(@RequestParam("key") String key) {
        try {
            redisTemplate.delete(key);
            return Msg.success();
        } catch (Exception e) {
            return Msg.failWithMsg(e.getMessage());
        }
    }

}
