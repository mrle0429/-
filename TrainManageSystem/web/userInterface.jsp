<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/13
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's Interface page</title>
    <link rel="stylesheet" href="css/userMenu.css">
</head>
<body>

    <div class="title">
        <div class="text">
            <h1>UserMenu</h1>
            <h3>Welcome to the train booking management system，${user.getUserName()}</h3>
        </div>
    </div>


    <div class="menu">

        <div class="One">
            <div class="menu-item">
                <img src="staticSources/personInfo.png" width="100px" height="100px">
                <button><a href="<%=request.getContextPath()%>/UserInformation?userId=${user.getUserId()}">Personal Information</a></button>
            </div>
        </div>

        <div class="Two">
            <div class="menu-item">
                <img src="staticSources/orders.png" width="100px" height="100px" >
                <button><a href="<%=request.getContextPath()%>/TicketsInterface?userId=${user.getUserId()}">Order Information</a></button>
            </div>
        </div>

        <div class="Three">
            <div class="menu-item">
                <img src="staticSources/Schedules.png" width="100px" height="100px">
                <button><a href="<%=request.getContextPath()%>/SchedulesInterface?userId=${user.getUserId()}">Schedule Inquiry</a></button>
            </div>
        </div>

    </div>



</body>
</html>
