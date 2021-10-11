$(function () {
    //搜索的关键词
    window.keyword = "";
    //当前页码
    window.pageNum = 1;
    //初始化角色列表
    getRoles();

    //搜索按钮点击
    $("#searchBtn").click(function () {
        let val = $("#searchInput").val();
        window.keyword = val;
        getRoles();
    });

    //新增角色按钮点击
    $("#addRoleBtn").click(function () {
        //显示模态框
        $('#myAddRoleModal').modal('show');
    });

    //新增模态框保存按钮点击
    $("#myAddRoleModal .saveRoleBtn").click(function () {
        let roleName = $("#addRoleInput").val();
        //添加角色
        addRole(roleName);
        //关闭模态框
        $('#myAddRoleModal').modal('hide');
        //清空模态框内容
        $("#addRoleInput").val("");
    });

    //编辑按钮点击
    $(document).on("click", ".editRoleBtn", function () {
        //显示模态框
        $('#myUpdateRoleModal').modal('show');
        //回显名称
        let name = $(this).parent().prev().text();
        $("#updateRoleInput").val(name);
        //获取该条数据的id
        window.roleId = this.id;
    });

    //更新模态框保存按钮点击
    $("#myUpdateRoleModal .updateRoleBtn").click(function () {
        let roleName = $("#updateRoleInput").val();
        //更新角色
        updateRole(window.roleId, roleName);
        //关闭模态框
        $('#myUpdateRoleModal').modal('hide');
        //清空模态框内容
        $("#updateRoleInput").val("");
    });

    //单删按钮点击
    $(document).on("click", ".deleteRoleBtn", function () {
        //删除角色的名称
        let name = $(this).parent().prev().text();
        //删除角色的id
        let id = $(this).prev().attr("id");
        let deleteIds = [];
        deleteIds.push(id);
        if (confirm("确认删除 【" + name + "】 吗？")) {
            deleteRole(deleteIds);
        }
    });

    //多删按钮点击
    $("#deleteRolesBtn").click(function () {
        let checkedBox = $("#roleTBody input:checked");
        if (checkedBox.length === 0) {
            layer.msg("请至少选择一条记录删除！");
            return;
        }
        //选择删除角色的id数组
        let deleteIds = [];
        //选择删除角色的名称数组
        let deleteNames = [];
        $.each(checkedBox, function () {
            let id = $(this).parent().next().next().find(".editRoleBtn").attr("id");
            let name = $(this).parent().next().text();
            deleteIds.push(id);
            deleteNames.push(name);
        });
        if (confirm("确认删除 【" + deleteNames + "】 吗？")) {
            //删除所选角色
            deleteRole(deleteIds);
            //取消全选按钮选中状态
            $("#selectAllBtn").prop("checked", false);
        }
    });

    //全选/全不选效果
    $("#selectAllBtn").click(function () {
        let checkStatus = this.checked;
        $("#roleTBody .itemCheckBox").prop("checked", checkStatus);
    });
    $(document).on("click", ".itemCheckBox", function () {
        //多选框总数
        let checkBoxLength = $(".itemCheckBox").length;
        //多选框已选数
        let checkedBoxLength = $(".itemCheckBox:checked").length;
        //同步全选按钮
        $("#selectAllBtn").prop("checked", checkBoxLength === checkedBoxLength);
    });

});

//删除角色
function deleteRole(deleteIds) {
    $.ajax({
        url: "admin/delete/role.json",
        type: "POST",
        data: JSON.stringify(deleteIds),
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (result) {
            let code = result.code;
            //处理成功
            if (code === 100) {
                layer.msg(result.msg);
                getRoles();
            }
            //处理失败
            else {
                layer.msg(result.msg);
            }
        },
        fail: function (result) {
            layer.msg(result.status + " " + result.statusText);
        }
    });
}

//更新角色函数
function updateRole(id, roleName) {
    $.ajax({
        url: "admin/update/role.json",
        type: "POST",
        data: {
            "id": id,
            "name": roleName
        },
        success: function (result) {
            let code = result.code;
            //处理成功
            if (code === 100) {
                layer.msg(result.msg);
                getRoles();
            }
            //处理失败
            else {
                layer.msg(result.msg);
            }
        },
        error: function (result) {
            layer.msg(result.status + " " + result.statusText);
        }
    });
}

//添加角色函数
function addRole(roleName) {
    $.ajax({
        url: "admin/add/role.json",
        type: "POST",
        data: {"roleName": roleName},
        success: function (result) {
            let code = result.code;
            //处理成功
            if (code === 100) {
                layer.msg(result.msg);
                window.pageNum = 999999999;
                getRoles();
            }
            //处理失败
            else {
                layer.msg(result.msg);
            }
        },
        error: function (result) {
            layer.msg(result.status + " " + result.statusText);
        }
    });
}

//获取角色列表函数
function getRoles() {
    $.ajax({
        url: "admin/get/role.json",
        type: "GET",
        data: {
            "keyword": keyword,
            "pageNum": pageNum
        },
        success: function (result) {
            if (result.code === 100) {
                let pageInfo = result.data.rolePageInfo;
                //生成角色列表
                generateRolesListHtml(pageInfo);
            } else {
                layer.msg(result.msg);
            }
        }
    });
}

//生成角色列表函数
function generateRolesListHtml(pageInfo) {
    //角色列表数据
    let list = pageInfo.list;
    //角色信息的表格的tbody
    let roleTBody = $("#roleTBody");
    $(roleTBody).empty();
    $("#Pagination").empty();
    if (list.length == 0) {
        roleTBody.append($("<tr><td colspan='4' align='center'>抱歉，没有数据！</td></tr>"));
        return;
    }
    $.each(list, function (index) {
        let role_tr = "<tr>\n" +
            "              <td>" + (index + 1) + "</td>\n" +
            "              <td><input class='itemCheckBox' type=\"checkbox\"></td>\n" +
            "              <td>" + this.name + "</td>\n" +
            "              <td>\n" +
            "                  <button id = " + this.id + " type=\"button\" class=\"btn btn-success btn-xs assignAuthBtn\"><i\n" +
            "                          class=\" glyphicon glyphicon-check\"></i></button>\n" +
            "                  <button id = " + this.id + " type=\"button\" class=\"btn btn-primary btn-xs editRoleBtn\"><i\n" +
            "                          class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
            "                  <button type=\"button\" class=\"btn btn-danger btn-xs deleteRoleBtn\"><i\n" +
            "                          class=\" glyphicon glyphicon-remove\"></i></button>\n" +
            "              </td>\n" +
            "           </tr>";
        roleTBody.append(role_tr);
    });
    //生成分页条
    generatePagination(pageInfo);
}

//生成分页条函数
function generatePagination(pageInfo) {
    // 获取分页数据中的总记录数
    var totalRecord = pageInfo.total;
    // 声明 Pagination 设置属性的 JSON 对象
    var properties = {
        num_edge_entries: 3, // 边缘页数
        num_display_entries: 5, // 主体页数
        callback: pageSelectCallback, // 用户点击“翻页”按钮之后 执行翻页操作的回调函数
        current_page: pageInfo.pageNum - 1, // 当前页，pageNum 从 1 开始， 必须-1 后才可以赋值
        prev_text: "上一页",
        next_text: "下一页",
        items_per_page: pageInfo.pageSize // 每页显示几项
    };
    // 调用分页导航条对应的 jQuery 对象的 pagination()方法生成导航条
    $("#Pagination").pagination(totalRecord, properties);
}

// 翻页过程中执行的回调函数
// 点击“上一页”、“下一页”或“数字页码”都会触发翻页动作，从而导致当前函数被调用
// pageIndex 是用户在页面上点击的页码数值
function pageSelectCallback(pageIndex, jQuery) {
    // pageIndex 是当前页页码的索引，相对于 pageNum 来说，pageIndex 比 pageNum 小 1
    window.pageNum = pageIndex + 1;
    // 调用生成角色列表函数
    getRoles();
    // 取消当前超链接的默认行为
    return false;
}