package com.example.crowdfunding.mvc.interceptor;

import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.constant.CrowdConstant;
import com.example.crowdfunding.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wanghu
 * @discrption： 登录拦截器
 * @create 2021-09-22 16:10
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute(CrowdConstant.ADMIN_LOGIN_ACCOUNT);

        if (admin == null) {
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }

        return true;
    }

}
