package xinhocbong.mySql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xinhocbong.database.ConnectionUtil;
import xinhocbong.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//gọi các thông tin của 3 bảng gồm sinh viên, tổ chức, thông tin tổ chức
public class ScholarshipStudents {

    public static ObservableList<User> getDataAllUsersReceivedScholarship() {
        Connection conn = ConnectionUtil.conn;
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT SINHVIEN.IDMSSV, SINHVIEN.NAME, SINHVIEN.GENDER, SINHVIEN.BRITHDATE, SINHVIEN.OVERALLSCORE, SINHVIEN.FAMILYSITUATION,THONGTINHOCBONG.IDHocBong, THONGTINHOCBONG.NGAYNHAN, THONGTINHOCBONG.SOTIEN,TOCHUC.TENTOCHUC\n" +
//                    "FROM THONGTINHOCBONG\n" +
//                    " JOIN TOCHUC ON THONGTINHOCBONG.IDToChuc=TOCHUC.ID\n" +
//                    " JOIN SINHVIEN ON THONGTINHOCBONG.IDSinhVien=SINHVIEN.IDMSSV;");
            PreparedStatement ps = conn.prepareStatement("SELECT SINHVIEN.IDMSSV, SINHVIEN.NAME, SINHVIEN.GENDER, SINHVIEN.BRITHDATE, SINHVIEN.OVERALLSCORE, SINHVIEN.FAMILYSITUATION,THONGTINHOCBONG.IDHocBong, THONGTINHOCBONG.NGAYNHAN, THONGTINHOCBONG.SOTIEN,TOCHUC.TENTOCHUC\n" +
                    "FROM  SINHVIEN\n" +
                    " left JOIN THONGTINHOCBONG ON THONGTINHOCBONG.IDSinhVien=SINHVIEN.IDMSSV\n" +
                    " left JOIN TOCHUC ON THONGTINHOCBONG.IDToChuc=TOCHUC.ID\n" +
                    " where THONGTINHOCBONG.NGAYNHAN is not null and  THONGTINHOCBONG.SOTIEN is not null and TOCHUC.TENTOCHUC is not null;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User( Float.parseFloat(rs.getString("OVERALLSCORE")),
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
