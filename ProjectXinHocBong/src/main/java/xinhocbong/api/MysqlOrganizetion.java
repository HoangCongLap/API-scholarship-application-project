package xinhocbong.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xinhocbong.database.ConnectionUtil;
import xinhocbong.users.Organizetion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlOrganizetion {
    public static ObservableList<Organizetion> getOrranizetions() {
        Connection conn = ConnectionUtil.conn;
        ObservableList<Organizetion> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from TOCHUC;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Organizetion( rs.getString("ID"), rs.getString("TENTOCHUC")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
