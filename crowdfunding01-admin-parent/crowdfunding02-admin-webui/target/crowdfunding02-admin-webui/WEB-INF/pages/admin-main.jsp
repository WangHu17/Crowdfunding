<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/18 0018
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="include-head.jsp"/>

<body>

<jsp:include page="include-nev.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="include-sidebar.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">控制面板</h1>

            <div class="row placeholders">
                <security:authorize access="hasRole('程序员')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                             alt="Generic placeholder thumbnail">
                        <h4>Label</h4>
                        <span class="text-muted">程序员</span>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('组长')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                             alt="Generic placeholder thumbnail">
                        <h4>Label</h4>
                        <span class="text-muted">组长</span>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('项目经理')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                             alt="Generic placeholder thumbnail">
                        <h4>Label</h4>
                        <span class="text-muted">项目经理</span>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('总经理')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                             alt="Generic placeholder thumbnail">
                        <h4>Label</h4>
                        <span class="text-muted">总经理</span>
                    </div>
                </security:authorize>
            </div>
        </div>
    </div>
</div>

</body>
</html>

