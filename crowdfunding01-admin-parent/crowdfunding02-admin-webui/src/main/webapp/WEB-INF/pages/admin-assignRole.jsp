<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="admin/do/assignRole.html" type="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <div class="form-group">
                            <label for="unAssignRolesList">未分配角色列表</label><br>
                            <select id="unAssignRolesList" class="form-control" multiple="multiple" size="10" style="width:150px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignedRoles}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toAssignedListBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toUnAssignedListBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="assignRolesList">已分配角色列表</label><br>
                            <select name="roleIdList" id="assignRolesList" class="form-control" multiple="multiple" size="10" style="width:150px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedRoles}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br>
                        <button id="submitBtn" type="submit" class="btn btn-success" style="margin-top: 25px;margin-left: 375px;">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    $(function (){
        //往右移按钮
        $("#toAssignedListBtn").click(function (){
            $("select:eq(0) > option:selected").appendTo("select:eq(1)");
        });
        //往左移按钮
        $("#toUnAssignedListBtn").click(function (){
            $("select:eq(1) > option:selected").appendTo("select:eq(0)");
        });
        //点击提交按钮时将已分配角色列表中的选项都选上
        $("#submitBtn").click(function (){
            $("#assignRolesList > option").prop("selected","selected");
        });
    });
</script>
</html>