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
    <title>Personal Information</title>
    <link rel="stylesheet" href="css/info.css">
</head>
<body>
<div class="container">

    <div class="pic">
        <img src="staticSources/userPic.png" width="200px" height="200px">
    </div>


    <div class="info">
        <h5>UserId：${user.getUserId()}</h5>
        <h5>Name：${user.getUserName()}</h5>
        <h5>Age：${user.getUserAge()}</h5>
        <h5>Sex：${user.getUserSex()}</h5>
        <h5>PhoneNumber：${user.getPhoneNumber()}</h5>
        <h5>Email：${user.getEmail()}</h5>
        <h5>Address：${user.getAddress()}</h5>
        <h5>Country：${user.getCountry()}</h5>
    </div>



    <br>
</div>

<br>

<div class="button-container">

     <div class="buttonM">
         <a href="<%=request.getContextPath()%>/UserInformationUpdate?userId=${user.getUserId()}" onclick="return confirm('Are you sure you want to change the info ? ')">Modify personal information</a>
         <a href="<%=request.getContextPath()%>/userPassword.jsp?userId=${user.getUserId()}&password=${user.getPassword()}" onclick="return confirm('Are you sure you want to change your password')">Password Reset</a>
         <a href="<%=request.getContextPath()%>/UserLogin?userId=${user.getUserId()}&password=${user.getPassword()}">Return</a>
     </div>


</div>


</body>
</html>
