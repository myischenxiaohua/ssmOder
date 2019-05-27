function nav(frm,page) {
    frm.page.value=page;
    frm.submit();
}




function delButn(id,msg,elment) {
    //console.log($().parents("tr").remove());
    thisElment=$(elment).parents("tr");//要删除的行元素
    requestData={id:id};//请求的参数
    delActionUrl="/user/del.html";//提交ajax的url
    fn=[delReturn];//删除提交ajax回调后执行的函数
    openDlg(msg);
}

function delReturn(data) {
    if(data.status){
        thisElment.remove();
        hideDlg();
        location.href="/user/list.html";
    }else {
        $("#removeProv").find("p").text(data.msg);
    }
    console.log(data);
}



function loadTable(data) {
var list=data.list;
var trs="";
for (v of list){
    trs+="<tr>" +
        "<td>" +
        "<span>dengchao</span>" +
        "</td>" +
        "<td>" +
        "<span>邓超</span>" +
        "</td>" +
        " <td>" +
        "<span>" +
        "女" +
        "</span>" +
        "</td>" +
        "<td>" +
        "<span>36</span>" +
        "</td>" +
        "<td>" +
        "<span>13688889999</span>" +
        "</td>" +
        "<td>" +
        " <span>普通员工</span>\n" +
        "</td>" +
        "<td>" +
        "<span><a class=\"viewUser\" href=\"/user/showUser.html/13\"><img src=\"/static/images/read.png\" alt=\"查看\" title=\"查看\"></a></span>\n" +
        "<span><a class=\"modifyUser\" href=\"/user/update.html?id=13\"><img src=\"/static/images/xiugai.png\" alt=\"修改\" title=\"修改\"></a></span>\n" +
        "<span><a class=\"deleteUser\" onclick=\"delButn(13,'邓超',this)\"><img src=\"/static/images/schu.png\" alt=\"删除\" title=\"删除\"></a></span>\n" +
        "</td>" +
        "</tr>"

}

$(".firstTr").append(trs);



}