<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/14
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户修改密码</title>
</head>
<body>

<% String userId = request.getParameter("userId"); %>
<% String password = request.getParameter("password"); %>

<form action="<%=request.getContextPath()%>/UserPassword" method="post">

    <input type="hidden" name="userId" value="<%=userId%>">
    <input type="hidden" name="password" value="<%=password%>">

    请输入原先密码：<input type="password" name="oldPassword">
    请输入新密码：<input type="password" name="newPassword">

    <input type="submit" value="提交"><br>
    <input type="reset" value="重置">

</form>

<span>
    <h4>${errorMsg}</h4>
</span>

</body>
</html>
