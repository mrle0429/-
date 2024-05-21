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
    <title>Train information management system</title>

    <link rel="stylesheet" href="css/identity.css">

  </head>

  <body>

  <div class="title">
      <div class="info">
        <h1>Train Information Management System</h1>
      </div>
  </div>

<div class="chose-page">


  <div class="form">
    <form method="post" action="<%=request.getContextPath()%>/adLogin.jsp">
      <input type="submit" name="Administrator" value="Administrator" >
    </form>

    <br>
    <br>
    <br>
    <br>
    <br>

    <form method="post" action="<%=request.getContextPath()%>/userLogin.jsp">
      <input type="submit" name="USER" value="User">
    </form>
  </div>


</div>



  <%--<%@include file="footer.html" %>--%>


  </body>
</html>
