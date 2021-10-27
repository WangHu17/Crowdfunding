package com.example.crowdfunding.mvc.config;

import com.example.crowdfunding.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-15 16:02
 */
@Configuration
@EnableWebSecurity
// 启用全局方法权限控制功能，并且设置 prePostEnabled = true。保证@PreAuthority、 @PostAuthority、@PreFilter、@PostFilter 生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 与 SpringSecurity 环境下用户登录相关
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        // 临时使用内存版登录的模式测试代码
//        builder.inMemoryAuthentication().withUser("wanghu").password("5517").roles("admin");

        // 正式功能中使用基于数据库的认证
        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder); // 使用带盐值的加密算法


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
                .antMatchers("/admin/get/user.html")
                .hasAuthority("user:get")
                .antMatchers("/admin/do/add.html")
                .hasAuthority("user:add")
                .antMatchers("/admin/del/{id}/{pageNum}/{keyword}.html")
                .hasAuthority("user:delete")
                .antMatchers("/admin/do/update.html")
                .hasAuthority("user:edit")
                .antMatchers("/admin/get/role.json")
                .hasAuthority("role:get")
                .antMatchers("/admin/add/role.json")
                .hasAuthority("role:add")
                .antMatchers("/admin/update/role.json")
                .hasAuthority("role:edit")
                .antMatchers("/admin/delete/role.json")
                .access("hasRole('项目经理') OR hasAuthority('role:delete')") // 要求具有‘项目经理’角色或‘role:delete’的权限
                .anyRequest()   // 其他任意请求
                .authenticated()    // 需要认证后访问
                .and()
                .exceptionHandling()    // 开启异常处理（只对在SpringSecurity配置类中设置的权限控制起作用，通过注解设置的走springMVC的异常处理）
                .accessDeniedHandler(new AccessDeniedHandler() {    // 设置出现异常后去的页面和显示的信息
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                        request.setAttribute("exception",new Exception(CrowdConstant.MESSAGE_ACCESS_DENIED));
                        request.getRequestDispatcher("/WEB-INF/pages/system-error.jsp").forward(request,response);
                    }
                })
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