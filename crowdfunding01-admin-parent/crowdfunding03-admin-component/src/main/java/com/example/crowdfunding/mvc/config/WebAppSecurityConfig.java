package com.example.crowdfunding.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-15 16:02
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    // 与 SpringSecurity 环境下用户登录相关
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        // 临时使用内存版登录的模式测试代码
//        builder.inMemoryAuthentication().withUser("wanghu").password("5517").roles("admin");

        // 正式功能中使用基于数据库的认证
        builder.userDetailsService(userDetailsService);

    }

    // 与 SpringSecurity 环境下请求授权相关
    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security
                .authorizeRequests()    // 对请求进行授权
                .antMatchers("/admin/to/login.html")    // 针对登录页进行设置
                .permitAll()    //无条件访问
                .antMatchers("/static/**")  // 针对静态资源进行设置
                .permitAll()    // 无条件访问
                .anyRequest()   // 其他任意请求
                .authenticated()    // 需要认证后访问
                .and()
                .csrf()
                .disable()  //禁用csrf验证
                .formLogin()    // 开启表单登录的功能
                .loginPage("/admin/to/login.html")  // 指定登录页面
                .loginProcessingUrl("/security/do/login.html")  // 指定处理登录请求的地址
                .defaultSuccessUrl("/admin/to/main.html",true)   // 指定登录成功后前往的地址
                .usernameParameter("loginAcct") // 账号的请求参数名称
                .passwordParameter("userPswd")  // 密码的请求参数名称
                .and()
                .logout()   // 开启退出登录功能
                .logoutUrl("/security/do/logout.html")  // 指定退出登录地址
                .logoutSuccessUrl("/admin/to/login.html")   // 指定退出成功以后前往的地址
                ;

    }

}