<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/24
  Time: 14:14
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

            <fm:form id="pwdForm" action="${pageContext.request.contextPath }/user/saveUser.html" method="post" modelAttribute="user" enctype="multipart/form-data" >




                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="oldPwd">旧密码：</label>
                    <input type="text" name="oldPwd" id="oldPwd" value="">
                    <!-- 放置提示信息 -->
                    <font color="red"></font>
                </div>
                <div>
                    <label for="userPassword">新密码：</label>
                    <input type="text" name="userPassword" id="userPassword" value="">
                    <!-- 放置提示信息 -->
                    <font color="red"></font>
                </div>
                <div>
                    <label for="rUserPassword">确认密码：</label>
                    <input type="text" name="rUserPassword" id="rUserPassword" value="">
                    <!-- 放置提示信息 -->
                    <font color="red"></font>
                </div>

                <div class="providerAddBtn">
                    <input type="button" name="update" id="update" value="修改" >
                    <input type="button" id="back" name="back" value="返回" >
                </div>
            </fm:form>
        </div>




    </div>
</section>
<script src="${pageContext.request.contextPath}/static/js/jquery3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script src="${pageContext.request.contextPath}/static/js/updatePwd.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/page.js"></script>
<%@include file="../common/footer.jsp"%>
