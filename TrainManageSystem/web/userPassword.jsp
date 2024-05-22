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
    <title>Modify password</title>
    <link rel="stylesheet" href="css/passwordUpdate.css">
</head>
<body>

<% String userId = request.getParameter("userId"); %>
<% String password = request.getParameter("password"); %>

<div class="form">
    <form action="<%=request.getContextPath()%>/UserPassword" method="post">

        <input type="hidden" name="userId" value="<%=userId%>">
        <input type="hidden" name="password" value="<%=password%>">

        Please enter your old password：<input type="password" name="oldPassword">
        Please enter your new password：<input type="password" name="newPassword">

        <input type="submit" value="Submit"><br>
        <input type="reset" value="Reset">

    </form>
</div>


<span>
    <h4>${errorMsg}</h4>
</span>

</body>
</html>
