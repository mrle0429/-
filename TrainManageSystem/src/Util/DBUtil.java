package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    private static final String URL = "jdbc:mysql://localhost:3306/trainbookinginformation?useUnicode=true&useSSL=false&characterEncoding=GBK&serverTimezone=Asia/Shanghai";
    private static final String USER = "root" ;
    private static final String USER_PASSWORD = "0323" ;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,USER_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(AutoCloseable closeable){
        try {
            if(closeable != null){
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
