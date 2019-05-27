<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/22
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<fm:form method="post" action="/user/fm.html" modelAttribute="user" >

    <fm:input path="userCode"/><fm:errors path="userCode"/>
    <fm:input path="userName"/><fm:errors path="userName"/>
    <fm:password path="userPassword"/><fm:errors path="userPassword"/>


<input type="submit" value="添加">
</fm:form>

</body>
</html>
