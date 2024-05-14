package Service;

import Model.User;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
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

//    public static User updateUserInformation(){
//
//    }

    public static User updateUserById(User oldUser){
        Connection con = DBUtil.getConnection();
        try {
            String sql = "update user set userName=?, userAge=?, userSex=?, phoneNumber=?, email=?, address=?, country=? where userId = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,oldUser.getUserName());
            ps.setInt(2,oldUser.getUserAge());
            ps.setString(3,oldUser.getUserSex());
            ps.setString(4,oldUser.getPhoneNumber());
            ps.setString(5,oldUser.getEmail());
            ps.setString(6,oldUser.getAddress());
            ps.setString(7,oldUser.getCountry());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean checkSameID(int generatedID){
        Connection con = DBUtil.getConnection();
        String sql = "select userId from user";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("userId");
                if(userId == generatedID){
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
                    con.rollback(); //
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
