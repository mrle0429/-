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
    <title>UserLogin</title>
    <link rel="stylesheet" href="css/loginUser.css">
</head>
<body>

<div class="title">
    <div class="Img">
        <img src="staticSources/smallLogin.png" width="150px" height="150px">
    </div>
</div>

<div class="login-page">
    <div class="form">
        <form action="<%=request.getContextPath()%>/UserLogin" method="post">

                <input type="text" name="userId" placeholder="User">

                <input type="password" name="password" placeholder="Password">

                <button>Login</button>


                <br>


            <p>Don't have an account?  <a href="<%=request.getContextPath()%>/SignIn">Register</a></p>

        </form>
    </div>
</div>



   <span>
       <h1 style="color: red">${errorMessage}</h1>
   </span>

  <span>
      <h1 style="color: red;">${errorSignIn}</h1>
  </span>


  <%@include file="footer.html"%>
</body>
</html>
