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
    <title>查询车次信息</title>
    <script src="js/jquery-3.7.1.min.js" type="text/javascript"></script>



</head>

<body>

<h1>车次信息查询</h1>

<form action="<%=request.getContextPath()%>/SchedulesSelect" method="post">
    <table>
        <input type="hidden" name="userId" value="${userId}"/>
        <tr>
            <td>起始站： <input type="text" name="departure" placeholder="请输入起始站"></td>
            <td>终点站： <input type="text" name="arrival" placeholder="请输入终点站"></td>
            <td>
                <label for="departure-date">选择发车日期:</label>
                <input type="date" id="departure-date" name="departure-date">
            </td>

            <%--<td>出发日期：<input type="text" name="departureDate" placeholder="年/月/日"></td>--%>
            <td><input type="submit" value="查询"></td>
        </tr>

    </table>
</form>

<br>
<br>
<br>
<br>
<br>
<%--展示车次信息信息--%>



<span>
    <h1>${errorMsg}</h1>
</span>

<span>
    <h1>${None}</h1>
</span>



<a href="<%=request.getContextPath()%>/UserInterface?userId=${userId}">返回主界面</a>


</body>

</html>
