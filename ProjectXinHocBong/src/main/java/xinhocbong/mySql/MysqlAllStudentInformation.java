package xinhocbong.mySql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xinhocbong.database.ConnectionUtil;
import xinhocbong.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//xuất từ mysql lên các thông OVERALLSCORE, IDMSSV,NAME,GENDER,BRITHDATE, FAMILYSITUATION, IDHocBong, ngayNhan, soTien, tenToChuc
public class MysqlAllStudentInformation {
    public static ObservableList<User> getDataAllUsers() {
        Connection conn = ConnectionUtil.conn;
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SINHVIEN.IDMSSV, SINHVIEN.NAME, SINHVIEN.GENDER, " +
                    "SINHVIEN.BRITHDATE, SINHVIEN.OVERALLSCORE, SINHVIEN.FAMILYSITUATION,THONGTINHOCBONG.IDHocBong, " +
                    "THONGTINHOCBONG.NGAYNHAN, THONGTINHOCBONG.SOTIEN,TOCHUC.TENTOCHUC\n" +
                    "FROM  SINHVIEN\n" +
                    " left JOIN THONGTINHOCBONG ON THONGTINHOCBONG.IDSinhVien=SINHVIEN.IDMSSV\n" +
                    " left JOIN TOCHUC ON THONGTINHOCBONG.IDToChuc=TOCHUC.ID;");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        Float.parseFloat(rs.getString("OVERALLSCORE")),
                        rs.getString("IDMSSV"),
                        rs.getString("NAME"),
                        rs.getString("GENDER"),
                        rs.getString("BRITHDATE"),
                        rs.getString("FAMILYSITUATION"),
                        rs.getString("IDHocBong"),
                        rs.getString("ngayNhan"),
                        rs.getString("soTien"),
                        rs.getString("tenToChuc")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
}
