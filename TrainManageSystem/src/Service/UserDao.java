package Service;

import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
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
