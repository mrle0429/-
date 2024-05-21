<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/8
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdministratorLogin</title>
    <link rel="stylesheet" href="css/loginAdmin.css">
</head>
<body>

<div class="title">
    <img src="staticSources/adminlogin.png" width="260" height="260">
</div>

<div class="login-page">
    <div class="form">
        <form action="<%=request.getContextPath()%>/AdministratorLogin" method="post">

            User: <input type="text" name="username">
            Password: <input type="password" name="password">


            <button>Login</button>

            <%--<input type="submit" value="Login" style="font-family: 楷体;font-size: 20px">--%>
            <br>

        </form>
    </div>

    <span>
       <h1 style="color: red">${errorMessage}</h1>
   </span>

</div>



<%@include file="footer.html"%>

</body>
</html>
