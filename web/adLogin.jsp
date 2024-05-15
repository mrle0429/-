<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdministratorLogin</title>
</head>
<body>
   <form action="<%=request.getContextPath()%>/AdministratorLogin" method="post">
       <p style="font-size: 25px;font-family: 楷体;font-weight: 800">
           User: <input type="text" name="username"> </p>
       <p style="font-size: 25px;font-family: 楷体;font-weight: 800">
           Password: <input type="password" name="password"></p>

       <p>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="登录"
                                                                  style="font-family: 楷体;font-size: 20px">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置" style="font-family: 楷体;font-size: 20px">
           <br>
       <p>

   </form>

   <span>
       <h1 style="color: red">${errorMessage}</h1>
   </span>
</body>
</html>
