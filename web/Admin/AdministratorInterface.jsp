<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="Util.DBUtil" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员界面</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>欢迎 ${AdWelcome}</h1>
<h2>列车时刻表</h2>
<table>
    <thead>
    <tr>
        <th>Schedule ID</th>
        <th>Train ID</th>
        <th>Departure Station</th>
        <th>Arrival Station</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
        <th>Available Seats</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <%
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 获取数据库连接
            con = DBUtil.getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM schedules";
            rs = stmt.executeQuery(sql);

            // 检查是否有结果
            if (!rs.isBeforeFirst()) {
                out.println("<tr><td colspan='8'>No schedules found.</td></tr>");
            } else {
                // 遍历结果集，显示时刻表
                while (rs.next()) {
                    int scheduleId = rs.getInt("scheduleId");
                    String trainId = rs.getString("trainId");
                    String departureStation = rs.getString("departureStation");
                    String arrivalStation = rs.getString("arrivalStation");
                    String departureTime = rs.getString("departureTime");
                    String arrivalTime = rs.getString("arrivalTime");
                    int availableSeats = rs.getInt("avaliableSeats");
    %>
    <tr>
        <td><%= scheduleId %></td>
        <td><%= trainId %></td>
        <td><%= departureStation %></td>
        <td><%= arrivalStation %></td>
        <td><%= departureTime %></td>
        <td><%= arrivalTime %></td>
        <td><%= availableSeats %></td>
        <td>
            <form action="EditSchedule.jsp" method="post" style="display:inline;">
                <input type="hidden" name="scheduleId" value="<%= scheduleId %>">
                <input type="submit" value="编辑">
            </form>
            <form action="DeleteScheduleServlet" method="post" style="display:inline;">
                <input type="hidden" name="scheduleId" value="<%= scheduleId %>">
                <input type="submit" value="删除">
            </form>
        </td>
    </tr>
    <%
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<tr><td colspan='8'>Error: " + e.getMessage() + "</td></tr>");
        } finally {
            DBUtil.close(con);
        }
    %>
    </tbody>
</table>

<h2>添加新列车时刻表</h2>
<form action="AddScheduleServlet" method="post">
    <label for="trainId">Train ID:</label>
    <input type="text" id="trainId" name="trainId" required><br>
    <label for="departureStation">Departure Station:</label>
    <input type="text" id="departureStation" name="departureStation" required><br>
    <label for="arrivalStation">Arrival Station:</label>
    <input type="text" id="arrivalStation" name="arrivalStation" required><br>
    <label for="departureTime">Departure Time:</label>
    <input type="datetime-local" id="departureTime" name="departureTime" required><br>
    <label for="arrivalTime">Arrival Time:</label>
    <input type="datetime-local" id="arrivalTime" name="arrivalTime" required><br>
    <label for="availableSeats">Available Seats:</label>
    <input type="number" id="availableSeats" name="availableSeats" required><br>
    <input type="submit" value="添加">
</form>

<h2>用户信息</h2>
<table>
    <thead>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Age</th>
        <th>Sex</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>Address</th>
        <th>Country</th>
    </tr>
    </thead>
    <tbody>
    <%
        try {
            // 获取数据库连接
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM user";
            rs = stmt.executeQuery(sql);

            // 遍历结果集，显示用户信息
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                int userAge = rs.getInt("userAge");
                String userSex = rs.getString("userSex");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String country = rs.getString("country");
    %>
    <tr>
        <td><%= userId %></td>
        <td><%= userName %></td>
        <td><%= userAge %></td>
        <td><%= userSex %></td>
        <td><%= phoneNumber %></td>
        <td><%= email %></td>
        <td><%= address %></td>
        <td><%= country %></td>
    </tr>
    <%
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con);
        }
    %>
    </tbody>
</table>
</body>
</html>
