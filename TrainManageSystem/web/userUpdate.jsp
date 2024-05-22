<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/14
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update Info</title>
    <link rel="stylesheet" href="css/infoUpdate.css">
</head>
<body>

<div class="form">
    <form action="<%=request.getContextPath()%>/UpdateUser" method="post">
        <input type="hidden" name="userId" value="${user.getUserId()}"/>
        <input type="hidden" name="password" value="${user.getPassword()}"/>
        <br>
        Name <input type="text" name="userName" value="${user.getUserName()}"/><br>
        Age <input type="text" name="userAge" value="${user.getUserAge()}"/><br>
        Sex <input type="text" name="userSex" value="${user.getUserSex()}"/><br>
        PhoneNumber <input type="text" name="phoneNumber" value="${user.getPhoneNumber()}"/><br>
        Email <input type="text" name="email" value="${user.getEmail()}"/><br>
        Address <input type="text" name="address" value="${user.getAddress()}"/><br>
        Country <input type="text" name="country" value="${user.getCountry()}"/><br>

        <input type="submit" value="Submit"><br>
        <input type="reset" value="Reset">
    </form>
</div>

</body>
</html>
