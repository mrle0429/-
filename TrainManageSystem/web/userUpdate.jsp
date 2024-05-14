<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/14
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户修改数据</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/UpdateUser" method="post">
    <input type="hidden" name="userId" value="${user.getUserId()}"/>
    <input type="hidden" name="password" value="${user.getPassword()}"/>
    <br>
    姓名<input type="text" name="userName" value="${user.getUserName()}"/><br>
    年龄<input type="text" name="userAge" value="${user.getUserAge()}"/><br>
    性别<input type="text" name="userSex" value="${user.getUserSex()}"/><br>
    电话号码<input type="text" name="phoneNumber" value="${user.getPhoneNumber()}"/><br>
    邮箱<input type="text" name="email" value="${user.getEmail()}"/><br>
    居住地址<input type="text" name="address" value="${user.getAddress()}"/><br>
    所在国家<input type="text" name="country" value="${user.getCountry()}"/><br>

    <input type="submit" value="提交"><br>
    <input type="reset" value="重置">
</form>
</body>
</html>
