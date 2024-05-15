<%@ page import="Service.AdministratorDao" %>
<%@ page import="Model.Schedule" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Schedule</title>
</head>
<body>

<%
    int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
    Schedule schedule = AdministratorDao.getScheduleByID(scheduleId);
%>
<h2>Please update</h2>
<form action="<%=request.getContextPath()%>/UpdateSchedule" method="post">
    Schedule ID : <input type="text" name="scheduleId" value="<%= schedule.getScheduleId() %>" readonly><br>
    Train ID: <input type="text" name="trainId" value="<%= schedule.getTrainId() %>" required><br>
    Departure Station: <input type="text" name="departureStation" value="<%= schedule.getDepartureStation() %>" required><br>
    Arrival Station: <input type="text" name="arrivalStation" value="<%= schedule.getArrivalStation() %>" required><br>
    Departure Time: <input type="datetime-local" name="departureTime" value="<%= schedule.getDepartureTime() %>" required><br>
    Arrival Time: <input type="datetime-local" name="arrivalTime" value="<%= schedule.getArrivalTime() %>" required><br>
    Available Seats: <input type="number" name="availableSeats" value="<%= schedule.getAvailableSeats() %>" required><br>
    <span style="color:red">${errorUpdate}</span><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
