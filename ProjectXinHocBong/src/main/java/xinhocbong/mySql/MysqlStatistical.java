package xinhocbong.mySql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xinhocbong.database.ConnectionUtil;
import xinhocbong.users.UsersStatistical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//gọi các thông tin của bảng Sinhvien
public class MysqlStatistical {
    public static ObservableList<UsersStatistical> getDataUsersModule1() {
        Connection conn = ConnectionUtil.conn;
        ObservableList<UsersStatistical> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from sinhvien");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UsersStatistical(Float.parseFloat(rs.getString("OVERALLSCORE")),
                        rs.getString("IDMSSV"), rs.getString("NAME"),
                        rs.getString("GENDER"), rs.getString("BRITHDATE"),
                        rs.getString("FAMILYSITUATION")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
