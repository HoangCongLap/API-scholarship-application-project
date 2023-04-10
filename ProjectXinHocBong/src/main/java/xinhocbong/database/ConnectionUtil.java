package xinhocbong.database;


import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    public static Connection conn = null;

    static {
        conn = connectdb();
    }
// kết nối đến mysql ( tên bảng xhb, user: root, password:11112222
    public static Connection connectdb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xhb?autoReconnect=true&useSSL=false",
                    "root", "11112222");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
