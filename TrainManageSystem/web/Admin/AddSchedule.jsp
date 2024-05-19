<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Schedule</title>
</head>
<body>
<h2>Please enter information</h2>
<form action="<%=request.getContextPath()%>/AddSchedule" method="post">
    Schedule ID: <input type="text" name="scheduleId" required><br>
    Train ID: <input type="text" name="trainId" required><br>
    Departure Station: <input type="text" name="departureStation" required><br>
    Arrival Station: <input type="text" name="arrivalStation" required><br>
    Departure Time: <input type="datetime-local" name="departureTime" required><br>
    Arrival Time: <input type="datetime-local" name="arrivalTime" required><br>
    Available Seats: <input type="number" name="availableSeats" required><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
