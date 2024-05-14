<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/13
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
</head>
<body>

<h5>编号：${user.getUserId()}</h5>
<h5>姓名：${user.getUserName()}</h5>
<h5>年龄：${user.getUserAge()}</h5>
<h5>性别：${user.getUserSex()}</h5>
<h5>电话号码：${user.getPhoneNumber()}</h5>
<h5>邮箱：${user.getEmail()}</h5>
<h5>居住地址：${user.getAddress()}</h5>
<h5>所在国家：${user.getCountry()}</h5>
<br>

<a href="<%=request.getContextPath()%>/UserInformationUpdate?userId=${user.getUserId()}" onclick="return confirm('确定要修改数据吗')">修改个人信息</a>
<a href="<%=request.getContextPath()%>/userPassword.jsp?userId=${user.getUserId()}&password=${user.getPassword()}" onclick="return confirm('确定要修改密码吗')">修改密码</a>
<a href="<%=request.getContextPath()%>/UserLogin?userId=${user.getUserId()}&password=${user.getPassword()}">返回主界面</a>

</body>
</html>
