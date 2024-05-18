package Service;

import Model.Schedule;
import Model.User;
import Util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDao {
    public static int getTotalNumberOfSchedules(){
        int count = 0;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM schedules";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static int getTotalNumberOfScheduledUser(int scheduleId){
        int count = 0;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM user u " +
                    "JOIN tickets t ON u.userId = t.userId " +
                    "WHERE t.scheduleId = ?;";
            st = conn.prepareStatement(sql);
            st.setInt(1, scheduleId);
            rs = st.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public static List<Schedule> getSchedulesByPage(int currentPage, int recordsPerPage){
        ArrayList<Schedule> schedules = new ArrayList<>();
        int start = (currentPage - 1) * recordsPerPage;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM schedules LIMIT ? OFFSET ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, recordsPerPage);
            st.setInt(2, start);
            rs = st.executeQuery();

            while (rs.next()){
                schedules.add(createScheduleByResSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return schedules;
    }

    public static List<User> getScheduledUserByPage(int scheduleId, int currentPage, int recordsPerPage){
        List<User> users = new ArrayList<>();
        int start = (currentPage - 1) * recordsPerPage;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT u.userId, userName, userAge, userSex, phoneNumber, email, address, country " +
                    "FROM user AS u JOIN tickets AS t ON u.userId = t.userId WHERE t.scheduleId = ? LIMIT ? OFFSET ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, scheduleId);
            st.setInt(2, recordsPerPage);
            st.setInt(3, start);
            rs = st.executeQuery();

            if (rs.next()){
                users.add(createUserByResSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public static Schedule getScheduleByID(int scheduleId){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM schedules WHERE scheduleId = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, scheduleId);
            rs = st.executeQuery();

            if (rs.next()){
                return createScheduleByResSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static boolean updateSchedule(Schedule schedule){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        int scheduleId = schedule.getScheduleId();
        String trainId = schedule.getTrainId();
        String departureStation = schedule.getDepartureStation();
        String arrivalStation = schedule.getArrivalStation();
        LocalDateTime departureTime = schedule.getDepartureTime();
        LocalDateTime arrivalTime = schedule.getArrivalTime();
        int availableSeats = schedule.getAvailableSeats();

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM schedules WHERE scheduleId = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, scheduleId);
            rs = st.executeQuery();
            if (rs.next() && scheduleDataChanged(rs, trainId, departureStation, arrivalStation, departureTime, arrivalTime, availableSeats)){
                String newSql = "UPDATE schedules SET trainId = ?, departureStation = ?, arrivalStation = ?, departureTime = ?, arrivalTime = ?, availableSeats = ? WHERE scheduleId = ?";
                st = conn.prepareStatement(newSql);
                st.setString(1, trainId);
                st.setString(2, departureStation);
                st.setString(3, arrivalStation);
                st.setTimestamp(4, Timestamp.valueOf(departureTime));
                st.setTimestamp(5, Timestamp.valueOf(arrivalTime));
                st.setInt(6, availableSeats);
                st.setInt(7, scheduleId);

                return st.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean deleteScheduleByID(int scheduleId){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM schedules WHERE scheduleId = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, scheduleId);

            return st.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean insertSchedule(Schedule schedule){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        int scheduleId = schedule.getScheduleId();
        String trainId = schedule.getTrainId();
        String departureStation = schedule.getDepartureStation();
        String arrivalStation = schedule.getArrivalStation();
        LocalDateTime departureTime = schedule.getDepartureTime();
        LocalDateTime arrivalTime = schedule.getArrivalTime();
        int availableSeats = schedule.getAvailableSeats();

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO schedules (scheduleId, trainId, departureStation, arrivalStation, departureTime, arrivalTime, availableSeats) VALUES " +
                    "                      (?, ?, ?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql);
            st.setInt(1, scheduleId);
            st.setString(2, trainId);
            st.setString(3, departureStation);
            st.setString(4, arrivalStation);
            st.setTimestamp(5, Timestamp.valueOf(departureTime));
            st.setTimestamp(6, Timestamp.valueOf(arrivalTime));
            st.setInt(7, availableSeats);

            return st.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static boolean scheduleDataChanged(ResultSet rs, String trainId, String departureStation, String arrivalStation, LocalDateTime departureTime, LocalDateTime arrivalTime, int availableSeats) throws SQLException {
        String trainIdDB = rs.getString("trainId");
        String departureStationDB = rs.getString("departureStation");
        String arrivalStationDB = rs.getString("arrivalStation");
        LocalDateTime departureTimeDB = LocalDateTime.parse(rs.getString("departureTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime arrivalTimeDB = LocalDateTime.parse(rs.getString("arrivalTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int availableSeatsDB = rs.getInt("availableSeats");

        return !trainIdDB.equals(trainId) || !departureStationDB.equals(departureStation) || !arrivalStationDB.equals(arrivalStation) ||
                !departureTimeDB.equals(departureTime) || !arrivalTimeDB.equals(arrivalTime) || !(availableSeatsDB == availableSeats);
    }

    private static Schedule createScheduleByResSet(ResultSet rs) throws SQLException {
        int scheduleId = rs.getInt("scheduleId");
        String trainId = rs.getString("trainId");
        String departureStation = rs.getString("departureStation");
        String arrivalStation = rs.getString("arrivalStation");
        LocalDateTime departureTime = LocalDateTime.parse(rs.getString("departureTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime arrivalTime = LocalDateTime.parse(rs.getString("arrivalTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int availableSeats = rs.getInt("availableSeats");

        return new Schedule(scheduleId, trainId, departureStation, arrivalStation, departureTime, arrivalTime, availableSeats);
    }

    private static User createUserByResSet(ResultSet rs) throws SQLException {
        int userId = rs.getInt("userId");
        String userName = rs.getString("userName");
        int userAge = rs.getInt("userAge");
        String userSex = rs.getString("userSex");
        String phoneNumber = rs.getString("phoneNumber");
        String email = rs.getString("email");
        String address = rs.getString("address");
        String county = rs.getString("country");
        return new User(userId, userName, userAge, userSex, phoneNumber, email, address, county);
    }
}
