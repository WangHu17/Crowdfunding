package com.example.crowdfunding.api;

import com.example.crowdfunding.util.Msg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-29 15:52
 */
@FeignClient("wh-crowd-redis")
public interface RedisRemoteService {

    /**
     * 设置不带过期时间的字符串数据
     */
    @RequestMapping("/set/redis/key/value/remote")
    Msg setRedisKeyValueRemote(@RequestParam("key") String key,
                               @RequestParam("value") String value);

    /**
     * 设置带过期时间的字符串数据
     */
    @RequestMapping("/set/redis/key/value/with/timeout/remote")
    Msg setRedisKeyValueRemoteWithTimeout(@RequestParam("key") String key,
                                          @RequestParam("value") String value,
                                          @RequestParam("time") long time,
                                          @RequestParam("timeUnit") TimeUnit timeUnit);

    /**
     * 获取数据
     */
    @RequestMapping("/get/redis/string/value/by/key/remote")
    Msg getRedisStringValueByKeyRemote(@RequestParam("key") String key);

    /**
     * 删除数据
     */
    @RequestMapping("/remove/redis/key/remote")
    Msg removeRedisKeyRemote(@RequestParam("key") String key);

}
