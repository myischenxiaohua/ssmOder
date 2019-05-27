<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/24
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery3.4.1.js"></script>
</head>
<body>
<script>
    $.ajax({
        type:"get",//请求类型
        url:"/user/check.html",//请求的地址
        data:{userCode:"zs"},//请求参数
        dataType:"json",//请求后返回的数据类型
        success:function (rst) {//请求成功后回调函数，rst是请求返回的数据
                $("#box").text(rst.msg);
        },
        error:function (data) {//请求失败后回调函数，data是错误信息
            //data 返回错误的信息

        }
    })
</script>


<div id="box">

</div>

</body>
</html>
