<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/7
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <h1>Hello World !!</h1>

  <form method="post" action="<%=request.getContextPath()%>/adLogin.jsp">
    <input type="submit" name="Administrator" value="管理员" >
  </form>

  <br>
  <br>
  <br>
  <br>
  <br>

  <form method="post" action="<%=request.getContextPath()%>/userLogin.jsp">
    <input type="submit" name="USER" value="用户">
  </form>

  </body>
</html>
