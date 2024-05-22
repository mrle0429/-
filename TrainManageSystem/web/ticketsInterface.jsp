
<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/16
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="css/order.css">
</head>
<body>

    <table border="3" align="center">
        <thead>
        <tr>
            <td>ticketId</td>
            <td>userName</td>
            <td>departureCity</td>
            <td>arrivalCity</td>
            <td>departureTime</td>
            <td>arrivalTime</td>
            <td>seatNumber</td>
            <td>purchaseDate</td>
        </tr>
        </thead>

        <tbody>

        <c:forEach items="${list}" var="ticket">
         <tr>
             <td>${ticket.getTicketId()}</td>
             <td>${ticket.getUserName()}</td>
             <td>${ticket.getDepartureCity()}</td>
             <td>${ticket.getArrivalCity()}</td>
             <td>${ticket.getDepartureTime()}</td>
             <td>${ticket.getArrivalTime()}</td>
             <td>${ticket.getSeatNumber()}</td>
             <td>${ticket.getPurchaseDate()}</td>

             <c:choose>
               <c:when test="${ticket.getDepartureTime().isAfter(localDateTime)}">
                   <td>
                       <a href="<%=request.getContextPath()%>/Refund?userId=${userId}&ticketId=${ticket.getTicketId()}">Refund</a>
                   </td>
               </c:when>
             </c:choose>
        </tr>
        </c:forEach>


        </tbody>


    </table>

    <a href="<%=request.getContextPath()%>/UserInterface?userId=${userId}">Return</a>


</body>
</html>
