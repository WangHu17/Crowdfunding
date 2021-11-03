package com.example.crowdfunding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-27 17:09
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthConsumerApplication.class,args);
    }

}
