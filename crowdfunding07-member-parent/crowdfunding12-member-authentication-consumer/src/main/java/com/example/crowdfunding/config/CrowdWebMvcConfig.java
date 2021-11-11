package com.example.crowdfunding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-02 15:09
 */
@Component
@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        // 跳转到用户注册页面
        registry.addViewController("auth/member/to/register/page.html").setViewName("register");
        // 跳转到用户登录页面
        registry.addViewController("auth/member/to/login/page.html").setViewName("login");
        // 跳转到用户中心
        registry.addViewController("auth/member/to/member/center/page.html").setViewName("member-center");
        // 跳转到我的众筹
        registry.addViewController("auth/member/to/my/crowd/page.html").setViewName("my-crowdfunding");

    }
}
