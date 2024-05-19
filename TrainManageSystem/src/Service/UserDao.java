package Service;

import Model.User;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static int getTotalNumberOfUser(){
        int count = 0;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM user";
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

    public static List<User> getUsersByPage(int currentPage, int recordsPerPage){
        List<User> users = new ArrayList<>();
        int start = (currentPage - 1) * recordsPerPage;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user LIMIT ? OFFSET ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, recordsPerPage);
            st.setInt(2, start);
            rs = st.executeQuery();

            while (rs.next()){
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

    private static User createUserByResSet(ResultSet rs) throws SQLException {
        String userId = rs.getString("userId");
        String userName = rs.getString("userName");
        int userAge = rs.getInt("userAge");
        String userSex = rs.getString("userSex");
        String phoneNumber = rs.getString("phoneNumber");
        String email = rs.getString("email");
        String address = rs.getString("address");
        String county = rs.getString("country");
        return new User(userId, userName, userAge, userSex, phoneNumber, email, address, county);
    }





    public static User selectUserById(String Id){

        Connection con = DBUtil.getConnection();

        try {
            String sql = "select * from user where userId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,Id);

            ResultSet rs= ps.executeQuery();

            if(rs.next()){
                System.out.println("!!!!!!");
                String userId = rs.getString("userId");
                String userName = rs.getString("userName");
                Integer userAge = rs.getInt("userAge");
                String userSex = rs.getString("userSex");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String country = rs.getString("country");
                String password = rs.getString("password");

                User user = new User(userId,userName,userAge,userSex,phoneNumber,email,address,country,password);
                return user;
            }
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(con);
        }
        return null;
    }

    public static void updatePassword(User user, String newPassword){
        user.setPassword(newPassword);
        updateUserById(user);
    }



    public static boolean updateUserById(User newUser){
        Connection con = DBUtil.getConnection();
        try {
            String sql = "update user set userName=?, userAge=?, userSex=?, phoneNumber=?, email=?, address=?, country=? ,password = ? where userId = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,newUser.getUserName());
            ps.setInt(2,newUser.getUserAge());
            ps.setString(3,newUser.getUserSex());
            ps.setString(4,newUser.getPhoneNumber());
            ps.setString(5,newUser.getEmail());
            ps.setString(6,newUser.getAddress());
            ps.setString(7,newUser.getCountry());
            ps.setString(8,newUser.getPassword());
            ps.setString(9,newUser.getUserId());

            int rs = ps.executeUpdate();
            if(rs > 0 ){
                return true;
            }

        }  catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(con);
        }
        return false;
    }


    public static boolean checkSameID(String generatedID){
        Connection con = DBUtil.getConnection();
        String sql = "select userId from user";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String userId = rs.getString("userId");
                if(userId.equals(generatedID)){
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(con);
        }
        return true;
    }

    public static boolean insertUer(String userId, String userName,Integer userAge,String userSex,String phoneNumber,String email,String address,String country, String password){
        Connection con = DBUtil.getConnection();
        try {

            String sql1 = "insert into user(userId, userName, userAge, userSex, phoneNumber, email, address, country, password) values(?,?,?,?,?,?,?,?,?) ";

            PreparedStatement ps1 = con.prepareStatement(sql1);

            ps1.setString(1,userId);
            ps1.setString(2,userName);
            ps1.setInt(3,userAge);
            ps1.setString(4,userSex);
            ps1.setString(5,phoneNumber);
            ps1.setString(6,email);
            ps1.setString(7,address);
            ps1.setString(8,country);
            ps1.setString(9,password);

            int rs1 = ps1.executeUpdate();
            if(rs1 <= 0){
                return false;
            }
            return true;
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(con);
        }
    }
}
