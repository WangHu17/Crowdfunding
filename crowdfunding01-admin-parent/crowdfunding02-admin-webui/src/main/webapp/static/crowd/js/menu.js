$(function () {

    $.ajax({
        url: "admin/get/menu.json",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.code === 100) {
                let setting = {};
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

});