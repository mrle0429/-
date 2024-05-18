<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Util.DBUtil" %>
<%@ page import="Service.AdministratorDao" %>
<%@ page import="Model.Schedule" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Train Schedules</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        form {
            margin: 0;
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

        List<Schedule> schedulesByPage = AdministratorDao.getSchedulesByPage(currentPage, recordsPerPage);
        for (Schedule schedule : schedulesByPage) {

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
