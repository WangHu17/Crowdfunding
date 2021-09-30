$(function () {

    getMenu();

    //添加节点按钮点击
    $(document).on("click",".addBtn",function (){
        //显示添加模态框
        $("#menuAddModal").modal("show");
        //获取当前节点id
        window.pId = this.id;
        //阻止跳转
        return false;
    });

    //添加节点保存按钮点击
    $("#menuSaveBtn").click(function (){
        //获取添加节点的名字
        let name = $("#menuAddModal input[name=name]").val();
        //获取添加节点的url
        let url = $("#menuAddModal input[name=url]").val();
        //获取添加节点的icon
        let icon = $("#menuAddModal input[name=icon]:checked").val();
        //发送ajax请求
        addMenu(window.pId,name,url,icon);
        //隐藏表单
        $("#menuAddModal").modal("hide");
        //重置表单
        $("#menuResetBtn").click();
    });

});


// 获取菜单
function getMenu() {
    $.ajax({
        url: "admin/get/menu.json",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.code === 100) {
                let setting = {
                    "view": {
                        "addDiyDom": myAddDiyDom,
                        "addHoverDom": myAddHoverDom,
                        "removeHoverDom": myRemoveHoverDom
                    },
                    "data": {
                        "key": {
                            "url": "none"
                        }
                    }
                };
                let menu = result.data.root;
                $.fn.zTree.init($("#treeDemo"), setting, menu);
            } else {
                layer.msg(result.msg);
            }
        },
        fail: function (result) {
            layer.msg(result.status + " " + result.statusText);
        }
    });
}

//添加节点
function addMenu(pId,name,url,icon){
    $.ajax({
        url: "admin/add/menu.json",
        type: "POST",
        data: {
            "pid": pId,
            "name": name,
            "url": url,
            "icon": icon
        },
        success: function (result) {
            if (result.code === 100) {
                layer.msg(result.msg);
                getMenu();
            }else {
                layer.msg(result.msg);
            }
        },
        fail: function (result) {
            layer.msg(result.status + " " + result.statusText);
        }
    });
}

// 修改为自己的图标
function myAddDiyDom(treeId, treeNode) {
    let spanId = treeNode.tId + "_ico";
    $("#" + spanId).removeClass().addClass(treeNode.icon)
}

//移入节点时添加操作图标
function myAddHoverDom(treeId, treeNode) {
    //如果已经添加了图标就返回
    let spanId = "btnGroup" + treeNode.id;
    if ($("#"+spanId).length > 0) return;
    // 节点的孩子节点长度
    let childLength = treeNode.children.length;
    // 节点id
    let id = treeNode.id;
    // 节点的级别
    let level = treeNode.level;
    // 要添加的图标
    let addBtn = "<a id='" + id + "' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    let removeBtn = "<a id='" + id + "' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删 除 节 点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    let editBtn = "<a id='" + id + "' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修 改 节 点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    let btnHtml = "";
    //添加图标的规则
    if (level === 0) {
        btnHtml = addBtn;
    } else if (level === 1 && childLength > 0) {
        btnHtml = editBtn + addBtn;
    } else if (level === 1) {
        btnHtml = editBtn + removeBtn + addBtn;
    } else if (level === 2) {
        btnHtml = editBtn + removeBtn;
    }
    //再目标节点后面添加图标
    let tree_a = treeNode.tId + "_a";
    $("#" + tree_a).after("<span id='btnGroup" + id + "'>"+ btnHtml +"</span>");
}

//移出节点时移除图标
function myRemoveHoverDom(treeId,treeNode) {
    let spanId = "btnGroup" + treeNode.id;
    $("#"+spanId).remove();
}