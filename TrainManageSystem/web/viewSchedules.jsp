<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/15
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Result Of Schedules Query</title>
    <link rel="stylesheet" href="css/resultSch.css">
</head>
<body>
<table border="3" align="center">

    <thead>
    <tr>
        <td>scheduleId</td>
        <td>trainId</td>
        <td>departureStation</td>
        <td>arrivalStation</td>
        <td>departureTime</td>
        <td>arrivalTime</td>
        <td>availableSeats</td>
    </tr>
    </thead>

    <tbody>


    <c:forEach items="${list}" var="schedule">
        <tr>
            <td>${schedule.getScheduleId()}</td>
            <td>${schedule.getTrainId()}</td>
            <td>${schedule.getDepartureStation()}</td>
            <td>${schedule.getArrivalStation()}</td>
            <td>${schedule.getDepartureTime()}</td>
            <td>${schedule.getArrivalTime()}</td>
            <td>${schedule.getAvailableSeats()}</td>

            <c:choose>
               <c:when test="${schedule.getDepartureTime().isAfter(currentDate) && schedule.getAvailableSeats()>0}">
                   <td>
                       <a href="<%=request.getContextPath()%>/Purchase?userId=${userId}&scheduleId=${schedule.getScheduleId()}" onclick="return confirm('确定要购票吗？')">Buy</a>
                   </td>
               </c:when>
            </c:choose>


        </tr>
    </c:forEach>

    </tbody>

</table>

</body>
</html>
