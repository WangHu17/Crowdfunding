$(function () {
    //搜索的关键词
    window.keyword = "";
    //当前页码
    window.pageNum = 1;

    getRoles();

    //搜索按钮点击
    $("#searchBtn").click(function (){
        let val = $("#searchInput").val();
        window.keyword = val;
        getRoles();
    });
});

//获取角色列表
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
                //生成分页条
                generatePagination(pageInfo);
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
    if (list.length == 0) {
        roleTBody.append($("<tr><td colspan='4'>抱歉，没有数据！</td></tr>"));
        return;
    }
    $.each(list, function (index) {
        let role_tr = "<tr>\n" +
            "              <td>" + (index + 1) + "</td>\n" +
            "              <td><input type=\"checkbox\"></td>\n" +
            "              <td>" + this.name + "</td>\n" +
            "              <td>\n" +
            "                  <button type=\"button\" class=\"btn btn-success btn-xs\"><i\n" +
            "                          class=\" glyphicon glyphicon-check\"></i></button>\n" +
            "                  <button type=\"button\" class=\"btn btn-primary btn-xs\"><i\n" +
            "                          class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
            "                  <button type=\"button\" class=\"btn btn-danger btn-xs\"><i\n" +
            "                          class=\" glyphicon glyphicon-remove\"></i></button>\n" +
            "              </td>\n" +
            "           </tr>";
        roleTBody.append(role_tr);
    });
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