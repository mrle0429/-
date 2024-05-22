<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/14
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Schedules Information Query</title>

    <script src="js/jquery-3.7.1.min.js" type="text/javascript"></script>

    <link rel="stylesheet" href="css/SchedulesInter.css">



</head>

<body>

<div class="title">
    <h1>Schedules Information Query</h1>
</div>


<div class="form">
    <form action="<%=request.getContextPath()%>/SchedulesSelect" method="post">
        <table>
            <input type="hidden" name="userId" value="${userId}"/>
            <tr>
                <td>departure： <input type="text" name="departure" placeholder="Please enter departureCity"></td>
                <td>arrival： <input type="text" name="arrival" placeholder="Please enter arrivalCity"></td>
                <td>
                    <label for="departure-date">Departure Date:</label>
                    <input type="date" id="departure-date" name="departure-date">
                </td>

                <%--<td>出发日期：<input type="text" name="departureDate" placeholder="年/月/日"></td>--%>
                <td><input type="submit" value="Query"></td>
            </tr>

        </table>
    </form>

</div>

<br>
<br>
<br>
<br>
<br>

<span>
    <h1>${errorMsg}</h1>
</span>

<span>
    <h1>${None}</h1>
</span>


<div class="re">
    <a href="<%=request.getContextPath()%>/UserInterface?userId=${userId}">Return</a>
</div>



</body>

</html>
