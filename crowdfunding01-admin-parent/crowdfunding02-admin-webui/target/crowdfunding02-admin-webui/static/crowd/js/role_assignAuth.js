$(function () {

    // 权限分配按钮点击事件(绿色按钮)
    $("#roleTBody").on("click", ".assignAuthBtn", function () {
        window.roleId = this.id;
        // 打开权限分配模态框
        $("#myAssignAuthModal").modal("show");
        // 获取全部权限信息并生成zTree
        getAllAuth();
    });

    // 权限分配默认框的保存按钮点击事件
    $("#saveAuthBtn").click(function () {
        assignAuths();
        // 关闭模态框
        $("#myAssignAuthModal").modal("hide");
    });

});

// 获取全部权限信息并生成zTree
function getAllAuth() {
    $.ajax({
        url: "admin/get/all/auth.json",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.code === 100) {
                // 权限信息
                let authList = result.data.authList;
                // 对zTree的设置
                let setting = {
                    "data": {
                        "simpleData": {
                            // 开启简单 JSON 功能
                            "enable": true,
                            // 使用 categoryId 属性关联父节点，不用默认的 pId 了
                            "pIdKey": "categoryId"
                        },
                        "key": {
                            // 使用 title 属性显示节点名称，不用默认的 name 作为属性名了
                            "name": "title"
                        }
                    },
                    // 显示勾选框
                    "check": {
                        "enable": true
                    }
                }
                // 生成树型结构
                $.fn.zTree.init($("#authTreeDemo"), setting, authList);
                // 获取zTreeObj对象
                let zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
                // 展开节点
                zTreeObj.expandAll(true);

                // 获取当前角色已分配权限并勾选上对应权限框
                getAssignedAuth();
            } else {
                layer.msg(result.msg);
            }
        },
        fail: function (result) {
            layer.msg(result.status + " " + result.statusText);
        }
    });
}

// 获取当前角色已分配权限并勾选上对应权限框
function getAssignedAuth() {
    $.ajax({
        url: "admin/get/assigned/auth.json",
        type: "POST",
        data: {"roleId": window.roleId},
        dataType: "json",
        success: function (result) {
            if (result.code === 100) {
                let authIdList = result.data.assignedAuths;
                $.each(authIdList, function () {
                    // 获取zTreeObj对象
                    let zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
                    // 根据 id 查询树形结构中对应的节点
                    let node = zTreeObj.getNodeByParam("id", this);
                    // 将 treeNode 设置为被勾选
                    let checked = true;
                    // checkTypeFlag 设置为 false，表示不“联动”，不联动是为了避免把不该勾选的勾选上
                    let checkTypeFlag = false;
                    zTreeObj.checkNode(node, checked, checkTypeFlag);
                });
            } else {
                layer.msg(result.msg);
            }
        },
        fail: function (result) {
            layer.msg(result.status + " " + result.statusText);
        }
    });
}

//保存选择的权限
function assignAuths() {
    // 用于保存选择的权限id
    let checkedAuthIds = [];
    // 获取zTreeObj对象
    let zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
    // 获取勾选了的节点
    let checkedNodes = zTreeObj.getCheckedNodes();
    // 遍历获取的节点对象将id放入数组
    $.each(checkedNodes, function () {
        checkedAuthIds.push(this.id);
    });
    // 将角色id和勾选的节点id数组封装成json对象
    let responseBody = {
        "roleId": [window.roleId],
        "authIds": checkedAuthIds,
    }
    responseBody = JSON.stringify(responseBody);
    // 发送请求
    $.ajax({
        url: "admin/do/assign/auth.json",
        type: "post",
        data: responseBody,
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            layer.msg(result.msg);
        }
        , fail: function (result) {
            layer.msg(result.status + " " + result.statusText);
        }
    });
}