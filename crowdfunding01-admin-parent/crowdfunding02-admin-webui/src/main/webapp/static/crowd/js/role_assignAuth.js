$(function (){

    //权限分配按钮点击事件(绿色按钮)
    $("#roleTBody").on("click",".assignAuthBtn",function (){
        //打开权限分配模态框
        $("#myAssignAuthModal").modal("show");
        getAllAuth();
    });

});

//获取全部权限信息并生成zTree
function getAllAuth(){
    $.ajax({
        url: "admin/get/all/auth.json",
        type: "GET",
        dataType: "json",
        success: function (result){
            if (result.code === 100) {
                //权限信息
                let authList = result.data.authList;
                //对zTree的设置
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
                //生成树型结构
                $.fn.zTree.init($("#authTreeDemo"),setting,authList);
                //获取zTreeObj对象
                let zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
                //展开节点
                zTreeObj.expandAll(true);
            }else {
                layer.msg(result.msg);
            }
        },
        fail: function (result){
            layer.msg(result.status + " " + result.statusText);
        }
    });
}