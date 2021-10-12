<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="include-head.jsp"/>
<link rel="stylesheet" href="static/css/pagination1.css">
<script src="static/jquery/jquery.pagination.js"></script>
<link rel="stylesheet" href="static/ztree/zTreeStyle.css">
<script src="static/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/crowd/js/role.js"></script>
<script type="text/javascript" src="static/crowd/js/role_assignAuth.js"></script>
<body>
<%--顶栏--%>
<jsp:include page="include-nev.jsp"/>
<div class="container-fluid">
    <div class="row">
        <%--左侧导航栏--%>
        <jsp:include page="include-sidebar.jsp"/>
        <%--右侧主体区域--%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="searchInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="deleteRolesBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button id="addRoleBtn" type="button" class="btn btn-primary" style="float:right;">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="selectAllBtn" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="roleTBody"></tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <%-- 分页条 --%>
                                    <div id="Pagination" class="pagination"></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--新增模态框--%>
<div class="modal fade" id="myAddRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="addRoleInput" class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-9">
                            <input type="text" name="roleName" class="form-control" id="addRoleInput"
                                   placeholder="请输入角色名称" autofocus>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary saveRoleBtn">保存</button>
            </div>
        </div>
    </div>
</div>
<%--更新模态框--%>
<div class="modal fade" id="myUpdateRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">更新</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="updateRoleInput" class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-9">
                            <input type="text" name="roleName" class="form-control" id="updateRoleInput"
                                   placeholder="请输入角色名称" autofocus>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary updateRoleBtn">更新</button>
            </div>
        </div>
    </div>
</div>
<%--权限分配模态框--%>
<div class="modal fade" id="myAssignAuthModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">权限分配</h4>
            </div>
            <div class="modal-body">
                <%--权限树型列表--%>
                <ul id="authTreeDemo" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="saveAuthBtn" type="button" class="btn btn-primary updateRoleBtn">权限选择完成，保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>