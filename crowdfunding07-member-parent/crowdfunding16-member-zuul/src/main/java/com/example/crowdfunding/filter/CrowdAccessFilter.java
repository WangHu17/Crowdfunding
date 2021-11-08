package com.example.crowdfunding.filter;

import com.example.crowdfunding.constant.AllowAccessResources;
import com.example.crowdfunding.constant.CrowdConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-05 16:15
 */
@Component
public class CrowdAccessFilter extends ZuulFilter {
    @Override
    public String filterType() {

        // 这里返回“pre”意思是在目标微服务前执行过滤
        return "pre";

    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        String contextPath = request.getServletPath();

        boolean allow = AllowAccessResources.PASS_URL_SET.contains(contextPath);

        // 如果是允许访问的资源，放行
        if (allow)return false;

        // 如果是静态资源，也放行
        return !AllowAccessResources.judgeUrlWeatherStaticResource(contextPath);
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        HttpSession session = request.getSession();

        // 从session对象中获取用户登录信息
        Object memberLogin = session.getAttribute(CrowdConstant.MEMBER_LOGIN_ACCOUNT);

        // 如果没有，即未登录
        if (memberLogin == null){

            // 将提示信息放入session
            session.setAttribute("message",CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);

            HttpServletResponse response = requestContext.getResponse();

            try {
                //重定向到登录页面
                response.sendRedirect("/auth/member/to/login/page.html");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }
}
