<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2024/5/8
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Individual Information</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
    <div class="form">
        <form method="post" action="<%=request.getContextPath()%>/InsertUser?">
            <table>

                <p style="align-content: center; font-weight: bold; font-family: 'Times New Roman', sans-serif;font-size: 36px">${userId}:This is your account name, please remember, once lost can not be retrieved </p>

                <input type="hidden" name="userId" value="${userId}"/>
                <tr>
                    <td>Name</td>
                    <td>
                        <input type="text" name="userName" placeholder="Please enter your name">
                    </td>
                </tr>

                <tr>
                    <td>Age</td>
                    <td>
                        <input type="text" name="userAge" placeholder="Please enter your age">
                    </td>
                </tr>

                <tr>
                    <td>Sex</td>
                    <td>
                        <input type="radio" name="userSex" checked = "checked" value="male" />male
                        <input type="radio" name="userSex" value="female"/>female

                    </td>
                </tr>

                <tr>
                    <td>PhoneNumber</td>
                    <td>
                        <input type="text" name="phoneNumber" placeholder="Please enter your phoneNumber">
                    </td>
                </tr>

                <tr>
                    <td>Email</td>
                    <td>
                        <input type="text" name="email" placeholder="Please enter your email">
                    </td>
                </tr>

                <tr>
                    <td>Address</td>
                    <td>
                        <input type="text" name="address" placeholder="Please enter your Address">
                    </td>
                </tr>

                <tr>
                    <td>Country</td>
                    <td><input type="text" name="country" placeholder="Please enter your country"></td>
                </tr>


                <tr>
                    <td>Custom password</td>
                    <td>
                        <input type="password" name="password" placeholder="Please enter your password">
                    </td>
                </tr>


                <tr>
                    <td>
                        <input type="submit" value="Submit" />
                        <input type="reset" value="Reset" />
                    </td>
                </tr>

            </table>
        </form>


    </div>




</body>
</html>
