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
        // 前台访问地址
        String urlPath = "auth/member/to/register/page.html";
        // 目标页面名字
        String viewName = "register";
        // 跳转
        registry.addViewController(urlPath).setViewName(viewName);
    }
}
