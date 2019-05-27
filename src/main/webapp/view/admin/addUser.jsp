<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/22
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/static/css/user.css" rel="stylesheet">
<section class="main">
    <%@ include file="../common/left.jsp"%>
    <div class="main_right">
<div class="providerAdd">

    <fm:form id="userForm" action="${pageContext.request.contextPath }/user/saveUser.html" method="post" modelAttribute="user" enctype="multipart/form-data" >




    <!--div的class 为error是验证错误，ok是验证成功-->
    <div>
        <label for="userCode">用户编码：</label>
        <input type="text" name="userCode" id="userCode" value=""><fm:errors path="userCode"/>
        <!-- 放置提示信息 -->
        <font color="red"></font>
    </div>
    <div>
        <label for="userName">用户名称：</label>
        <input type="text" name="userName" id="userName" value=""><fm:errors path="userName"/>
        <font color="red"></font>
    </div>
        <div>
            <label for="userName">头像：</label>
            <input type="file" name="a_idPicPath" id="idPicPath" value=""><fm:errors path="idPicPath"/>
            <label for="userName">工作照：</label>
            <input type="file" name="a_idPicPath" id="idPicPath" value=""><fm:errors path="idPicPath"/>
            <font color="red"></font>
        </div>
    <div>
        <label for="userPassword">用户密码：</label>
        <input type="password" name="userPassword" id="userPassword" value="">
        <font color="red"></font>
        <fm:errors path="userPassword"/>
    </div>
    <div>
        <label for="ruserPassword">确认密码：</label>
        <input type="password" name="ruserPassword" id="ruserPassword" value="">
        <font color="red"></font>
    </div>
    <div>
        <label >用户性别：</label>
        <select name="gender" id="gender">
            <option value="1" selected="selected">男</option>
            <option value="2">女</option>
        </select>
    </div>
    <div>
        <label for="birthday">出生日期：</label>
        <input type="text" Class="Wdate" id="birthday" name="birthday"
               readonly="readonly" onclick="WdatePicker();">
        <font color="red"></font>
    </div>
    <div>
        <label for="phone">用户电话：</label>
        <input type="text" name="phone" id="phone" value="">
        <font color="red"></font>
    </div>
    <div>
        <label for="address">用户地址：</label>
        <input name="address" id="address"  value="">
    </div>
    <div>
        <label >用户角色：</label>
        <!-- 列出所有的角色分类 -->
        <select name="userRole" id="userRole">

        </select>
        <font color="red"></font>
    </div>
    <div class="providerAddBtn">
        <input type="button" name="add" id="add" value="保存" >
        <input type="button" id="back" name="back" value="返回" >
    </div>
    </fm:form>
</div>




    </div>
</section>
<script src="${pageContext.request.contextPath}/static/js/jquery3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/static/plug/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script src="${pageContext.request.contextPath}/static/js/user.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/page.js"></script>
<%@include file="../common/footer.jsp"%>
