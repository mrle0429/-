package Service;

import Model.Schedule;
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

    public static boolean checkSameID(int generatedID){
        Connection con = DBUtil.getConnection();
        String sql = "select cid from user";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int cid = rs.getInt("cid");
                if(cid == generatedID){
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

    public static boolean insertUer(String cid, String cname,Integer cage,String csex,String cphone,String password){
        Connection con = DBUtil.getConnection();
        try {

            String sql1 = "insert into user(cid, cname, cage, csex, cphone) values(?,?,?,?,?) ";
            String sql2 = "insert into useraccount(cid,password) values(?,?)";

            PreparedStatement ps1 = con.prepareStatement(sql1);
            PreparedStatement ps2 = con.prepareStatement(sql2);

            ps1.setString(1,cid);
            ps1.setString(2,cname);
            ps1.setInt(3,cage);
            ps1.setString(4,csex);
            ps1.setString(5,cphone);

            ps2.setString(1,cid);
            ps2.setString(2,password);

            int rs1 = ps1.executeUpdate();
            int rs2 = ps2.executeUpdate();
            if(rs1 <= 0 || rs2 <= 0){
                return false;
            }
            return true;
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback(); // �ع�����
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
