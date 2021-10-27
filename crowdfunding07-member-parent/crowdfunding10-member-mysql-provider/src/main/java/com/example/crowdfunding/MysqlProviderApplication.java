package com.example.crowdfunding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-27 17:09
 */
@MapperScan("com.example.crowdfunding.mapper")
@SpringBootApplication
public class MysqlProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlProviderApplication.class,args);
    }

}
