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
</head>
<body>
    <div>
        <form method="post" action="<%=request.getContextPath()%>/InsertUser?">
            <table>

                <p style="align-content: center;">${userId}:这是你的账户名，请记住，一旦丢失无法找回</p>

                <input type="hidden" name="userId" value="${userId}"/>
                <tr>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="userName" placeholder="请输入用户名">
                    </td>
                </tr>

                <tr>
                    <td>年龄</td>
                    <td>
                        <input type="text" name="userAge" placeholder="请输入年龄">
                    </td>
                </tr>

                <tr>
                    <td>性别</td>
                    <td>
                        <input type="radio" name="userSex" checked = "checked" value="male" />男
                        <input type="radio" name="userSex" value="female"/>女

                    </td>
                </tr>

                <tr>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="phoneNumber" placeholder="请输入电话号码">
                    </td>
                </tr>

                <tr>
                    <td>邮箱地址</td>
                    <td>
                        <input type="text" name="email" placeholder="请输入邮箱">
                    </td>
                </tr>

                <tr>
                    <td>居住地址</td>
                    <td>
                        <input type="text" name="address" placeholder="请输入居住地址">
                    </td>
                </tr>

                <tr>
                    <td>国家</td>
                    <td><input type="text" name="country" placeholder="请输入所在国家"></td>
                </tr>


                <tr>
                    <td>自定义密码</td>
                    <td>
                        <input type="password" name="password" placeholder="请输入密码">
                    </td>
                </tr>


                <tr>
                    <td>
                        <input type="submit" value="提交" />
                        <input type="reset" value="重置" />
                    </td>
                </tr>

            </table>
        </form>


    </div>




</body>
</html>
