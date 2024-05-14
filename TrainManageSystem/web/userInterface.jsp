<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/13
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's Interface page</title>
</head>
<body>

    <h1>简单的用户界面</h1>
    <h3>欢迎来到劣质12306系统，${user.getUserName()}</h3>

    <div>
        <table>
            <thead>
               <tr>铁路管理系统</tr>
            </thead>

            <tbody>
               <tr>
                   <td><a href="<%=request.getContextPath()%>/UserInformation?userId=${user.getUserId()}">个人信息</a></td>
               </tr>

               <tr>
                   <td><a href="<%=request.getContextPath()%>/OrdersTickets?userId=${user.getUserId()}">订单信息</a></td>
               </tr>

               <tr>
                   <td><a href="<%=request.getContextPath()%>/SchedulesInterface?userId=${user.getUserId()}">车次查询</a></td>
               </tr>

            </tbody>







        </table>
    </div>


</body>
</html>
