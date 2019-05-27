var oldPwd=$("#oldPwd");
var userPassword=$("#userPassword");
var rUserPassword=$("#rUserPassword");
var update=$("#update");
var back=$("#back")
oldPwd.next().text("*");
userPassword.next().text("*");
rUserPassword.next().text("*");

oldPwd.bind("blur",function () {

    ajaxAction("get","/user/checkPwd.html",{pwd:oldPwd.val()},[tips]);

}).bind("focus",function () {
    validateTip(oldPwd.next(),{"color":"#666666"},"*请输入原密码",true);
}).focus()


userPassword.bind("focus",function () {
    validateTip(userPassword.next(),{"color":"#666666"},"*请输入新密码",true);
}).bind("blur",function () {
    if(userPassword.val().length<7){
        validateTip(userPassword.next(),{"color":"#666666"},"*请输入7位以上的密码",false);
    }
})

rUserPassword.bind("focus",function () {
    validateTip(rUserPassword.next(),{"color":"#666666"},"*请输入和上面相同的密码",true);
}).bind("blur",function () {
    if(rUserPassword.val()!=userPassword.val()){
        validateTip(rUserPassword.next(),{"color":"#666666"},"*输入的密码不一致",false);
    }
})

update.click(function () {

//    执行ajax提交
        if (oldPwd.attr("validatestatus")!="true"){
            oldPwd.blur();
        }else if(userPassword.attr("validatestatus")!="true") {
            userPassword.blur();
        }else if(rUserPassword.attr("validatestatus")!="true") {
            rUserPassword.blur();
        }else {
            //都验证通过了，提交ajax
            ajaxAction("get","/user/updatePwdSave.html",{pwd:userPassword.val()},[msg])

        }

})




function tips(data) {
    validateTip(oldPwd.next(),{"color":"#666666"},data.msg,data.status);
}

function msg(data) {

    alert(data.msg);
    location.href="/user/list.html";


}

back.on("click",function(){
    if(referer != undefined
        && null != referer
        && "" != referer
        && "null" != referer
        && referer.length > 4){
        window.location.href = referer;
    }else{
        history.back(-1);
    }
});
