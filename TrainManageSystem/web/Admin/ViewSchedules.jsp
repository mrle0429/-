<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Util.DBUtil" %>
<%@ page import="Service.AdministratorDao" %>
<%@ page import="Model.Schedules" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Train Schedules</title>
    <style>
        /*table {*/
            /*width: 100%;*/
            /*border-collapse: collapse;*/
        /*}*/
        /*table, th, td {*/
            /*border: 1px solid black;*/
        /*}*/
        /*th, td {*/
            /*padding: 10px;*/
            /*text-align: left;*/
        /*}*/
        /*form {*/
            /*margin: 0;*/
        /*}*/
        /* Reset */
        body {
            margin: 0;
            padding: 0;
        }

        /* Body */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* 淡灰色背景 */
            color: #333; /* 文本颜色 */
            line-height: 1.6;
        }

        /* Header */
        h2 {
            text-align: center;
            margin-top: 20px;
            color: #007bff; /* 蓝色标题 */
            font-size: 24px;
        }

        /* Table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #dee2e6; /* 灰色边框 */
        }

        th {
            background-color: #f2f2f2;
        }

        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tbody tr:hover {
            background-color: #e2e6ea; /* 淡蓝色悬停背景 */
        }

        /* Pagination */
        .pagination {
            text-align: center;
            margin-top: 20px;
        }

        .pagination a {
            color: #007bff; /* 蓝色链接 */
            display: inline-block;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color 0.3s;
            border: 1px solid #007bff;
            margin-right: 5px;
            border-radius: 4px;
        }

        .pagination a.active {
            background-color: #007bff;
            color: #fff; /* 白色文字 */
        }

        .pagination a:hover:not(.active) {
            background-color: #007bff;
            color: #fff;
        }

    </style>
</head>
<body>

<h2>Current schedules:</h2>

<table>
    <thead>
    <tr>
        <th>Schedule ID</th>
        <th>Train ID</th>
        <th>Departure Station</th>
        <th>Arrival Station</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
        <th>Available Seats</th>
        <th>Operation</th>
    </tr>
    </thead>
    <tbody>
    <%
        int currentPage = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null){
            currentPage =  Integer.parseInt(request.getParameter("page"));
        }

        List<Schedules> schedulesByPage = AdministratorDao.getSchedulesByPage(currentPage, recordsPerPage);
        for (Schedules schedule : schedulesByPage) {

    %>
    <tr>
        <td>
            <a href="<%=request.getContextPath()%>/Admin/ScheduleInfo.jsp?scheduleId=<%= schedule.getScheduleId() %>"><%= schedule.getScheduleId() %></a>
        </td>
        <td><%= schedule.getTrainId() %></td>
        <td><%= schedule.getDepartureStation() %></td>
        <td><%= schedule.getArrivalStation() %></td>
        <td><%= schedule.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %></td>
        <td><%= schedule.getArrivalTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %></td>
        <td><%= schedule.getAvailableSeats() %></td>
        <td>
            <a href="<%=request.getContextPath()%>/Admin/UpdateSchedule.jsp?scheduleId=<%= schedule.getScheduleId() %>">Update</a>
            &nbsp;
            <a href="<%=request.getContextPath()%>/DeleteSchedule?scheduleId=<%= schedule.getScheduleId() %>" onclick="return confirm('Are you sure?');">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<div style="text-align: center">
    <%

        int totalNumberOfSchedules = AdministratorDao.getTotalNumberOfSchedules();
        int totalPage = totalNumberOfSchedules / recordsPerPage;
        if (totalNumberOfSchedules % recordsPerPage != 0){
            totalPage++;
        }
        int startPage = Math.max(1, currentPage - 1);
        int endPage = Math.min(totalPage, startPage + 2);

        if (currentPage > 1){
    %>
    <a href="<%=request.getContextPath()%>/Admin/ViewSchedules.jsp?page=<%=currentPage - 1%>">Previous</a>
    <%
        }

        for (int i = startPage; i <= endPage ; i++) {
    %>
    <a href="<%=request.getContextPath()%>/Admin/ViewSchedules.jsp?page=<%=i%>"><%=i%></a>
    <%
        }

        if (currentPage < totalPage){
    %>
    <a href="<%=request.getContextPath()%>/Admin/ViewSchedules.jsp?page=<%=currentPage + 1%>">Next</a>
    <%
        }
    %>
    <br>
    <a href="<%=request.getContextPath()%>/Admin/AddSchedule.jsp">Add a new schedule</a>

    <br>
    <a href="<%=request.getContextPath()%>/Admin/AdminHome.jsp">Back to home</a>
</div>


</body>
</html>
