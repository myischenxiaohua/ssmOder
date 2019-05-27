var path = $("#path").val();

var imgYes = "<img width='15px' src='"+path+"/static/images/y.png' />";
var imgNo = "<img width='15px' src='"+path+"/static/images/n.png' />";
var requestData,delActionUrl,fn; //定义提交的参数，url地址，回调执行的函数
var thisElment;//选择点击删除的当前元素
/**
 * 提示信息显示
 * element:显示提示信息的元素（font）
 * css：提示样式
 * tipString:提示信息
 * status：true/false --验证是否通过
 */
function validateTip(element,css,tipString,status){
    element.css(css);
    element.html(tipString);

    element.prev().attr("validateStatus",status);
}

var referer = $("#referer").val();


function ajaxAction(type,url,data,fn) {

    $.ajax({
        type:type,
        url:url,
        data:data,
        dataType:"json",
        success:function (data) {
            for(v of fn){
                v(data);
            }
        },
        error:function (data) {
            //    出错的时候干什么写在这里
        }

    })

}

function save(data) {
    validateTip(oldPwd.next(),{"color":"#666666"},data.msg,data.status);
}


function openDlg(msg) {

    $(".zhezhao").show();
    $("#removeProv").find("p").text("你确定要删除用户【"+msg+"】吗？")
    $("#removeProv").fadeIn();

    
}

function hideDlg() {
    $(".zhezhao").hide();
    $("#removeProv").fadeOut();

}

$("#yes").click(function () {

        ajaxAction("get",delActionUrl,requestData,fn);
})

$("#no").click(function () {
    console.log("asda");
        hideDlg();
})
