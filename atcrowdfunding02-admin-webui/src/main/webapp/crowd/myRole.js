// 通用分页方法
function generatePage() {

    // 1、远程获取分页信息
    const pageInfo = getPageInfoRemote();
    // console.log("111111"+pageInfo)
    // 2、填充表格
    fullTableData(pageInfo);
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
            console.log(response)
            var pageInfo = response.data;
            console.log(pageInfo);
            fullTableData(pageInfo);
        },
        "error": function (response) {
            console.log(response)
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
        let role = roleList.get(i);

        let roleId = role.id;
        let roleName = role.name;

        let numberId = "<td>" + (i + 1) + "</td>";
        let checkboxId = "<td><input type='checkbox'></td>";
        let roleNameId = "<td>" + roleName + "</td>";

        let checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>";
        let pencilBtn = "<button type='button' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>";
        let removeBtn = "<button type='button' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";

        let btnId = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + " " + "</td>";

        let tr = "<tr>" + numberId + checkboxId + roleNameId + btnId + "</tr>";

        $("#rolePageBody").append(tr);

    }


}

function generateNavigator(pageInfo) {

}

function pageSelectCallback() {

}