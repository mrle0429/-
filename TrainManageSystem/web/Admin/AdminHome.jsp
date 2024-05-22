<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home</title>
    <link rel="stylesheet" href="../css/adminHome.css">
</head>
<body>

<%
    String adminName = (String) session.getAttribute("adminName");
%>
<h1>Welcome <%= adminName %> !</h1>

<p> Please choice operation</p>
<a href="<%=request.getContextPath()%>/AdminScheduleManagement?option=viewSchedules">View Train Schedules</a> <br>
<a href="<%=request.getContextPath()%>/AdminScheduleManagement?option=viewUsers">View users' information</a>

</body>
</html>
