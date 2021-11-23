package com.example.crowdfunding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-09 14:58
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PayConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayConsumerApplication.class,args);
    }

}
