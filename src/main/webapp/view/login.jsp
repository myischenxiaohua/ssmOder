<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/9
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

</body>
</html>
<html>
<head>
    <title>登录</title>
    <link href=${pageContext.request.contextPath}"/static/css/login.css" rel="stylesheet">
</head>
<body>
<section class="login_box">
    <header><h1>超市管理系统</h1></header>
    <section class="login_form">
        <form method="post" action="/login.html">
            <div class="info">
                <%-- 局部异常 ${e.message } --%>
              <!-- 全局异常：--> ${exception.message }</div>
            <div class="login_form_input">
                <label>用户名：</label> <input  type="text" name="userCode" placeholder="请输入用户名" required></br>
            </div>
            <div class="login_form_input">
                <label style="margin-right: 5px;">密码：</label><input type="password" name="userPassword" placeholder="请输入密码" required>
            </div>
            <div class="butn">
                <input type="submit" value="登录">
                <input type="reset" value="重置">
            </div>


        </form>
    </section>

</section>



</body>
</html>
