<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/11
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/header.jsp"%>
<link href=${pageContext.request.contextPath}"/static/css/user.css" rel="stylesheet">
<section class="main">
    <%@ include file="../common/left.jsp"%>
    <div class="main_right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面</span>
        </div>
        <div class="search">
            <form method="get" action="/user/list.html">
                <input name="method" value="query" class="input-text" type="hidden">
                <span>用户名：</span>
                <input name="userName" class="input-text" type="text" value="">

                <span>用户角色：</span>
                <select name="role">
                    <option value="0">--请选择--</option>
                    <c:forEach var="role" items="${roleList}">

                        <option value="${role.id}">${role.roleName}</option>

                    </c:forEach>
                </select>

                <input type="hidden" name="page" value="1">
                <input value="查 询" type="submit" id="searchbutton">
                <a href="/user/add.html">添加用户</a>
            </form>
        </div>
        <table class="providerTable">
            <tr class="firstTr" >
                <th width="10%">用户编码</th>
                <th width="20%">用户名称</th>
                <th width="10%">性别</th>
                <th width="10%">年龄</th>
                <th width="10%">电话</th>
                <th width="10%">用户角色</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>
                        <span>${user.userCode}</span>
                    </td>
                    <td>
                        <span>${user.userName}</span>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${user.gender==1}">
                       <span>
								男
							</span>
                            </c:when>
                            <c:otherwise>
                       <span>
								女

							</span>
                            </c:otherwise>

                        </c:choose>

                    </td>
                    <td>
                        <span>36</span>
                    </td>
                    <td>
                        <span>13688889999</span>
                    </td>
                    <td>
                        <span>${user.role.roleName}</span>
                    </td>
                    <td>
                        <span><a class="viewUser" href="/user/showUser.html/${user.id}"  ><img src="${pageContext.request.contextPath}/static/images/read.png" alt="查看" title="查看"></a></span>
                        <span><a class="modifyUser" href="/user/update.html?id=${user.id}" ><img src="${pageContext.request.contextPath}/static/images/xiugai.png" alt="修改" title="修改"></a></span>
                        <span><a class="deleteUser"  onclick="delButn(${user.id},'${user.userName}',this)" ><img src="${pageContext.request.contextPath}/static/images/schu.png" alt="删除" title="删除"></a></span>
                    </td>
                    <div></div>
                </tr>
            </c:forEach>
        </table>


        <div class="page-bar">
            <ul class="page-num-ul clearfix">
                <li>共${count}条记录&nbsp;&nbsp; ${currentPageNo }/${totalPageCount}页</li>
                <c:if test="${currentPageNo > 1}">
                    <a href="/user/list.html?page=1">首页</a>
                    <a href="/user/list.html?page=${currentPageNo-1}">上一页</a>
                </c:if>
                <c:if test="${currentPageNo <totalPageCount }">
                    <a href="/user/list.html?page=${currentPageNo+1 }">下一页</a>
                    <a href="/user/list.html?page=${totalPageCount }">最后一页</a>
                </c:if>
                &nbsp;&nbsp;
            </ul>
            <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />页
	     <button type="button" class="page-btn" onClick='nav(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
		</span>
        </div>


    </div>
</section>

<div class="zhezhao" ></div>
<div class="remove" id="removeProv">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除供应商【石家庄帅益食品贸易有限公司】吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/page.js"></script>
<%@include file="../common/footer.jsp"%>
