//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.security.taglibs.authz;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.FrameworkServlet;

/**
 * 重写源码
 * 原因：在页面使用security:authorize标签，
 *      报 No visible WebSecurityExpressionHandler instance could be found in the application context.
 *      There must be at least one in order to support expressions in JSP 'authorize' tags.错误
 *      因为AbstractAuthorizeTag 类默认是查找“根级别”的 IOC 容器（spring的IOC容器）。
 *      而根级别的 IOC 容器中没有扫描 SpringSecurity 的配置类，所以没有相关的 bean。
 * 修改：修改为扫描springMVC的IOC容器
 */

public abstract class AbstractAuthorizeTag {
    private String access;
    private String url;
    private String method = "GET";

    public AbstractAuthorizeTag() {
    }

    protected abstract ServletRequest getRequest();

    protected abstract ServletResponse getResponse();

    protected abstract ServletContext getServletContext();

    public boolean authorize() throws IOException {
        boolean isAuthorized;
        if (StringUtils.hasText(this.getAccess())) {
            isAuthorized = this.authorizeUsingAccessExpression();
        } else if (StringUtils.hasText(this.getUrl())) {
            isAuthorized = this.authorizeUsingUrlCheck();
        } else {
            isAuthorized = false;
        }

        return isAuthorized;
    }

    public boolean authorizeUsingAccessExpression() throws IOException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return false;
        } else {
            SecurityExpressionHandler handler = this.getExpressionHandler();

            Expression accessExpression;
            try {
                accessExpression = handler.getExpressionParser().parseExpression(this.getAccess());
            } catch (ParseException var5) {
                IOException ioException = new IOException();
                ioException.initCause(var5);
                throw ioException;
            }

            return ExpressionUtils.evaluateAsBoolean(accessExpression, this.createExpressionEvaluationContext(handler));
        }
    }

    protected EvaluationContext createExpressionEvaluationContext(SecurityExpressionHandler<FilterInvocation> handler) {
        FilterInvocation f = new FilterInvocation(this.getRequest(), this.getResponse(), new FilterChain() {
            public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
                throw new UnsupportedOperationException();
            }
        });
        return handler.createEvaluationContext(SecurityContextHolder.getContext().getAuthentication(), f);
    }

    public boolean authorizeUsingUrlCheck() throws IOException {
        String contextPath = ((HttpServletRequest)this.getRequest()).getContextPath();
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return this.getPrivilegeEvaluator().isAllowed(contextPath, this.getUrl(), this.getMethod(), currentUser);
    }

    public String getAccess() {
        return this.access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method != null ? method.toUpperCase() : null;
    }

    private SecurityExpressionHandler<FilterInvocation> getExpressionHandler() throws IOException {
//        ApplicationContext appContext = SecurityWebApplicationContextUtils.findRequiredWebApplicationContext(this.getServletContext());

        // 1.获取 ServletContext 对象
        ServletContext servletContext = getServletContext();

        // 2.拼接 SpringMVC 在 ServletContext 域中的属性名
        String attrName = FrameworkServlet.SERVLET_CONTEXT_PREFIX + "springDispatcherServlet";

        // 3.从 ServletContext 域中获取 IOC 容器对象
        ApplicationContext appContext = (ApplicationContext) servletContext.getAttribute(attrName);

        Map<String, SecurityExpressionHandler> handlers = appContext.getBeansOfType(SecurityExpressionHandler.class);
        Iterator var3 = handlers.values().iterator();

        SecurityExpressionHandler h;
        do {
            if (!var3.hasNext()) {
                throw new IOException("No visible WebSecurityExpressionHandler instance could be found in the application context. There must be at least one in order to support expressions in JSP 'authorize' tags.");
            }

            h = (SecurityExpressionHandler)var3.next();
        } while(!FilterInvocation.class.equals(GenericTypeResolver.resolveTypeArgument(h.getClass(), SecurityExpressionHandler.class)));

        return h;
    }

    private WebInvocationPrivilegeEvaluator getPrivilegeEvaluator() throws IOException {
        WebInvocationPrivilegeEvaluator privEvaluatorFromRequest = (WebInvocationPrivilegeEvaluator)this.getRequest().getAttribute(WebAttributes.WEB_INVOCATION_PRIVILEGE_EVALUATOR_ATTRIBUTE);
        if (privEvaluatorFromRequest != null) {
            return privEvaluatorFromRequest;
        } else {
            ApplicationContext ctx = SecurityWebApplicationContextUtils.findRequiredWebApplicationContext(this.getServletContext());
            Map<String, WebInvocationPrivilegeEvaluator> wipes = ctx.getBeansOfType(WebInvocationPrivilegeEvaluator.class);
            if (wipes.size() == 0) {
                throw new IOException("No visible WebInvocationPrivilegeEvaluator instance could be found in the application context. There must be at least one in order to support the use of URL access checks in 'authorize' tags.");
            } else {
                return (WebInvocationPrivilegeEvaluator)wipes.values().toArray()[0];
            }
        }
    }
}
