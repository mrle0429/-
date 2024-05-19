package Service;

import Model.Order;
import Model.Schedules;
import Model.Tickets;
import Util.DBUtil;
import cn.hutool.db.Db;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SchedulesDao {


    public static void refund(String ticketId){

        Connection con = DBUtil.getConnection();

        try {
            String sql = "delete from tickets where ticketId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,ticketId);

            int rs = ps.executeUpdate();

            DBUtil.close(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Schedules> selectSchedules(String departure, String arrival){
        Connection con = DBUtil.getConnection();
        List<Schedules> schedulesList = new ArrayList<>();

        try {
            String sql =
                    "select sc.scheduleId,sc.trainId,os.city as startCity,ds.city as endCity,sc.departureTime,sc.arrivalTime,sc.availableSeats " +
                            "from schedules as sc "+
                            "inner join stations os on sc.departureStation=os.stationId "+
                            "inner join stations ds on sc.arrivalStation=ds.stationId "+
                            "where os.city=? and ds.city=?" ;
            PreparedStatement ps= con.prepareStatement(sql);

            ps.setString(1,departure);
            ps.setString(2,arrival);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                String scheduleId = rs.getString("scheduleId");
                String trainId = rs.getString("trainId");
                String departureStation = rs.getString("startCity");
                String arrivalStation= rs.getString("endCity");

                Timestamp timestamp1 ;
                timestamp1 = rs.getTimestamp("departureTime");
                LocalDateTime departureTime = timestamp1.toLocalDateTime();

                Timestamp timestamp2 ;
                timestamp2 = rs.getTimestamp("arrivalTime");
                LocalDateTime arrivalTime = timestamp2.toLocalDateTime();

                Integer availableSeats = rs.getInt("availableSeats");

                Schedules schedule = new Schedules(scheduleId,trainId,departureStation,arrivalStation,departureTime,arrivalTime,availableSeats);

                schedulesList.add(schedule);
            }

            return schedulesList;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con);
        }
        return null;
    }

    //¾«È·ËÑË÷
    public static List<Schedules> selectSchedules(String departure, String arrival , LocalDate selectedDate){
        Connection con = DBUtil.getConnection();
        List<Schedules> schedulesList = new ArrayList<>();

        try {

            String sql =
                    "select sc.scheduleId,sc.trainId,os.city as startCity,ds.city as endCity,sc.departureTime,sc.arrivalTime,sc.availableSeats " +
                            "from schedules as sc "+
                            "inner join stations os on sc.departureStation=os.stationId "+
                            "inner join stations ds on sc.arrivalStation=ds.stationId "+
                            "where os.city=? and ds.city=? and DATE(sc.departureTime)=?" ;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,departure);
            ps.setString(2,arrival);
            ps.setString(3, String.valueOf(Date.valueOf(selectedDate)));

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String scheduleId = rs.getString("scheduleId");
                String trainId = rs.getString("trainId");
                String departureStation = rs.getString("startCity");
                String arrivalStation= rs.getString("endCity");

                Timestamp timestamp1 ;
                timestamp1 = rs.getTimestamp("departureTime");
                LocalDateTime departureTime = timestamp1.toLocalDateTime();

                Timestamp timestamp2 ;
                timestamp2 = rs.getTimestamp("arrivalTime");
                LocalDateTime arrivalTime = timestamp2.toLocalDateTime();

                Integer availableSeats = rs.getInt("availableSeats");

                Schedules schedule = new Schedules(scheduleId,trainId,departureStation,arrivalStation,departureTime,arrivalTime,availableSeats);

                schedulesList.add(schedule);
            }
            DBUtil.close(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedulesList;
    }

    public static boolean checkSameTicketId(String randomNumber){
        Connection con = DBUtil.getConnection();

        try {
            String sql = "select ticketId from tickets";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String ticketId = rs.getString("ticketId");
                if(ticketId.equals(randomNumber)){
                    return false;
                }
            }
            DBUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean updateSeats(String scheduleId){
        Connection con = DBUtil.getConnection();
        String sql = "update schedules set availableSeats = availableSeats + 1 where scheduleId = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,scheduleId);

            int rs = preparedStatement.executeUpdate();

            DBUtil.close(con);
            if(rs<=0){
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean updateAvailableSeats(String scheduleId){
        Connection con = DBUtil.getConnection();
        String sql = "update schedules set availableSeats = availableSeats - 1 where scheduleId = ?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,scheduleId);

            int rs = ps.executeUpdate();

            if(rs > 0){
                return true;
            }

            DBUtil.close(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addTickets(Tickets ticket){
        Connection con = DBUtil.getConnection();
        String sql = "insert into tickets(ticketId, userId, scheduleId, seatNumber, purchaseDate, comments, status) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,ticket.getTicketId());
            ps.setString(2,ticket.getUserId());
            ps.setString(3,ticket.getScheduled());
            ps.setString(4,ticket.getSeatNumber());
            ps.setTimestamp(5, Timestamp.valueOf(ticket.getPurchaseDate()));
            ps.setString(6,ticket.getComments());
            ps.setString(7,ticket.getStatus());

            int rs = ps.executeUpdate();
            DBUtil.close(con);
            if(rs > 0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Order> selectTicketsByUserId(String userId){
        List<Order> ticketsList = new ArrayList<>();
        Connection con = DBUtil.getConnection();

        try {
            String sql = "select ti.ticketId,us.userName,os.city as startCity,ds.city as endCity,sc.departureTime,sc.arrivalTime ,ti.seatNumber,ti.purchaseDate "+
                    "from tickets as ti "+
                    "inner join schedules sc on sc.scheduleId=ti.scheduleId "+
                    "inner join stations os on sc.departureStation=os.stationId "+
                    "inner join stations ds on sc.arrivalStation=ds.stationId "+
                    "inner join user us on ti.userId = us.userId "+
                    "where ti.userId=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String ticketId = rs.getString("ticketId");
                String userName = rs.getString("userName");
                String startCity = rs.getString("startCity");
                String endCity = rs.getString("endCity");

                Timestamp departure = rs.getTimestamp("departureTime");
                LocalDateTime departureTime = departure.toLocalDateTime();

                Timestamp arrival = rs.getTimestamp("arrivalTime");
                LocalDateTime arrivalTime = arrival.toLocalDateTime();

                String seatNumber = rs.getString("seatNumber");

                Timestamp purchase = rs.getTimestamp("purchaseDate");
                LocalDateTime purchaseDate = purchase.toLocalDateTime();

                Order order = new Order(ticketId,userName,startCity,endCity,departureTime,arrivalTime,seatNumber,purchaseDate);
                ticketsList.add(order);
                System.out.println(ticketId);

            }
            DBUtil.close(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticketsList;
    }

}
