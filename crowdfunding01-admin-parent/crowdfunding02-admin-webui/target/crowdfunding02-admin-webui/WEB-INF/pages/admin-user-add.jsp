<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="include-head.jsp"/>

<body>

<jsp:include page="include-nev.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="include-sidebar.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/to/main.html">首页</a></li>
                <li><a href="admin/get/user.html">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form action="admin/do/add.html" method="post" role="form">
                        <p style="color: red">${requestScope.exception.message}</p>
                        <div class="form-group">
                            <label for="exampleInputAccount">登陆账号</label>
                            <input type="text" name="loginAcct" class="form-control" id="exampleInputAccount" placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword">登陆密码</label>
                            <input type="text" name="userPswd" class="form-control" id="exampleInputPassword" placeholder="请输入登陆密码">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputUserName">用户昵称</label>
                            <input type="text" name="userName" class="form-control" id="exampleInputUserName" placeholder="请输入用户昵称">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail">邮箱地址</label>
                            <input type="email" name="email" class="form-control" id="exampleInputEmail" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增
                        </button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>