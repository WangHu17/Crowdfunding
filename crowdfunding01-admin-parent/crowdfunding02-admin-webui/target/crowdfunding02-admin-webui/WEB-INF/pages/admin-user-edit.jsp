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
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form action="admin/do/update.html" method="post" role="form">
                        <input type="hidden" name="id" value="${requestScope.admin.id}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <div class="form-group">
                            <label for="exampleInputAccount">登陆账号</label>
                            <input type="text" name="loginAcct" class="form-control" id="exampleInputAccount" value="${requestScope.admin.loginAcct}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputUserName">用户名称</label>
                            <input type="text" name="userName" class="form-control" id="exampleInputUserName" value="${requestScope.admin.userName}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail">邮箱地址</label>
                            <input type="email" name="email" class="form-control" id="exampleInputEmail" value="${requestScope.admin.email}">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改
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