<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/20
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <link href=${pageContext.request.contextPath}"/static/css/base.css" rel="stylesheet">
    <link href=${pageContext.request.contextPath}"/static/css/header.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery3.4.1.js"></script>
</head>
<body>
<header>
    <h1>超市管理系统</h1>
    <div class="header_info"><span>${loginUser.userName}</span>欢迎你<a href=${pageContext.request.contextPath}"/loginOut.html">退出</a></div>
</header>
<section class="time">
    <span id="time_info">2019年05月20日&nbsp;10:02:17&nbsp;星期一</span>
    <span>温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</span>
</section>
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
<input type="hidden" id="referer" value="${pageContext.request.getHeader("Referer")}">