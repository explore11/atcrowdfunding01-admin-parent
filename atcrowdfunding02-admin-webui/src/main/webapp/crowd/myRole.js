// 通用分页方法
function generatePage() {

    // 1、远程获取分页信息
    getPageInfoRemote();
}

// 远程获取分页信息
function getPageInfoRemote() {
    $.ajax({
        "url": "role/get/page/info.json",
        "type": "post",
        "data": {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        "dataType": "json",
        "success": function (response) {
            // console.log(response)
            let pageInfo = response.data;
            // console.log(pageInfo);
            fullTableData(pageInfo);
        },
        "error": function (response) {
            // console.log(response)
            layer.msg("请求角色数据失败");
        }

    })
}

// 填充表格数据
function fullTableData(pageInfo) {
    //清除之前的旧数据
    $("#rolePageBody").empty();

    if (pageInfo == null || pageInfo.list == null || pageInfo.list.length === 0) {
        $("#rolePageBody").append("<tr><td colspan='4'> 抱歉 未能找到你需要的数据！ </td></tr>");
        return;
    }
    // 循环遍历
    let roleList = pageInfo.list;
    for (let i = 0; i < roleList.length; i++) {
        //获取对象
        let role = roleList[i];
        let roleId = role.id;
        let roleName = role.name;

        let numberTd = "<td>" + (i + 1) + "</td>";
        let checkboxTd = "<td><input type='checkbox'></td>";
        let roleNameTd = "<td>" + roleName + "</td>";

        let checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>";
        let pencilBtn = "<button type='button' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>";
        let removeBtn = "<button type='button' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";

        let btnTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + " " + "</td>";
        let tr = "<tr>" + numberTd + checkboxTd + roleNameTd + btnTd + "</tr>";

        $("#rolePageBody").append(tr);
    }

    // 调用页码
    generateNavigator(pageInfo);
}

//生成页码
function generateNavigator(pageInfo) {
    //获取总记录数
    let totalRecord = pageInfo.total;
    //声明一个json对象来存储pagination的属性设置
    var properties = {
        "num_edge_entries": 3,                //边缘页数
        "num_display_entries": 5,             //主体页数
        "callback": pageSelectCallback,       //回调函数
        "items_per_page": pageInfo.pageSize,//每页显示个数
        "current_page": pageInfo.pageNum - 1, //当前页数  默认是从0开始的，即0就是第一页
        "prev_text": "上一页",
        "next_text": "下一页",
    };
    //生成页码导航条
    $("#Pagination").pagination(totalRecord, properties);

}

// 回调函数
function pageSelectCallback(pageIndex, jQuery) {
    //根据传过来的pageIndex 计算得到pageNum
    // pageIndex 是pagination传给过来  “从0开始的” 页码
    window.pageNum = pageIndex + 1;
    // 再次调分页
    generatePage()
     //每一个页码都是超连接  取消超链接的默认跳转
    return false;
}