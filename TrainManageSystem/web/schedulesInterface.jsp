<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/14
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询车次信息</title>
    <script src="js/jquery-3.7.1.min.js" type="text/javascript"></script>

    <script>
        $(function() {
            $("#datepicker").datepicker({
                dateFormat: 'yy-mm-dd', // 设置日期格式
                onSelect: function(dateText) {
                    // 当用户选择日期时，发送 AJAX 请求
                    $.ajax({
                        type: 'POST',
                        url: 'searchTrainServlet', // 后端处理请求的 Servlet 地址
                        data: { date: dateText }, // 发送所选日期
                        success: function(response) {
                            // 在成功接收到响应后，处理返回的车次信息
                            console.log(response); // 这里可以根据需要进行处理，如显示车次信息等
                        }
                    });
                }
            });
        });
    </script>


</head>

<body>

<h1>车次信息查询</h1>

<form action="<%=request.getContextPath()%>/Schedules">
    <table>

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



</body>

</html>
