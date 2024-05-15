<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Individual Information</title>
</head>
<body>
    <div>
        <form method="post" action="<%=request.getContextPath()%>/InsertUser?">
            <table>

                <p style="align-content: center;">${cid}:这是你的账户名，请记住，一旦丢失无法找回</p>

                <input type="hidden" name="cid" value="${cid}"/>
                <tr>
                    <td>姓名</td>
                    <td>
                        <input type="text" name="cname" placeholder="请输入用户名">
                    </td>
                </tr>

                <tr>
                    <td>年龄</td>
                    <td>
                        <input type="text" name="cage" placeholder="请输入年龄">
                    </td>
                </tr>

                <tr>
                    <td>性别</td>
                    <td>
                        <input type="radio" name="csex" checked = "checked" value="male" />男
                        <input type="radio" name="csex" value="female"/>女

                    </td>
                </tr>

                <tr>
                    <td>联系方式</td>
                    <td>
                        <input type="text" name="cphone" placeholder="请输入电话号码">
                    </td>
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
