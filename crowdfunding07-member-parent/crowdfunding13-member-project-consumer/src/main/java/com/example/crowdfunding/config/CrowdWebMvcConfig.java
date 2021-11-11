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

        // 跳转到同意协议页面
        registry.addViewController("agreement/page.html").setViewName("project-agreement");
        // 跳转到项目发起页面
        registry.addViewController("launch/project/page.html").setViewName("project-launch");
        // 跳转到回报设置页面
        registry.addViewController("return/info/page.html").setViewName("project-return");

    }
}
