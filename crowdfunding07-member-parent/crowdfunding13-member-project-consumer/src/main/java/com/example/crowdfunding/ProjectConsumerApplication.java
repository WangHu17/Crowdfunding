package com.example.crowdfunding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-09 14:58
 */
@EnableFeignClients
@SpringBootApplication
public class ProjectConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectConsumerApplication.class,args);
    }

}
