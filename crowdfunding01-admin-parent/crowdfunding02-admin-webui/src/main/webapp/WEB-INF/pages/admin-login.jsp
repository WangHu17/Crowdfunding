<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/17 0017
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <title>管理员登录</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script src="static/jquery/jquery-3.6.0.min.js"></script>
    <script src="static/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/login.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="http://localhost" style="font-size:32px;">Creativity-Crowdfunding</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <form action="security/do/login.html" method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading">
            <i class="glyphicon glyphicon-log-in"></i>管理员登录
        </h2>
        <%--登录错误提示信息--%>
        <p style="color: red;">${requestScope.exception.message}</p>
        <%-- SpringSecurity的提示信息 --%>
        <p>${SPRING_SECURITY_LAST_EXCEPTION.message }</p>
        <div class="form-group has-success has-feedback">
            <input type="text" name="loginAcct" class="form-control" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" name="userPswd" class="form-control" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <button type="submit" class="btn btn-lg btn-success btn-block">登录</button>
    </form>
</div>

</body>
</html>
