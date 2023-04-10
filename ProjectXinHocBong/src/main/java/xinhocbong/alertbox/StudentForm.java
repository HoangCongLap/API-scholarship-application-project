package xinhocbong.alertbox;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import xinhocbong.api.CreateUsersStudentFormApi;
import xinhocbong.api.UpdateUsersStudentFormApi;
import xinhocbong.database.ConnectionUtil;
import xinhocbong.mySql.MysqlOrganizetion;
import xinhocbong.users.User;
import javafx.scene.control.TextField;
import xinhocbong.users.Organizetion;

import java.time.LocalDate;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.Math.random;
import static java.lang.String.valueOf;

public class StudentForm {
    public TextFlow text;
    public Text textData;

    @FXML
    public TextField textFieldMoney;
    @FXML
    public ComboBox<Organizetion> comboBoxOrganizaName;
    private User user;

    private Organizetion selectedOrganization;
    @FXML
    private DatePicker ReceivedDate;

    public void initForm(User user) {
        this.user = user;
        textData.setText("\n" + "   \tMSSV : " + user.getMssv() + "\n" + "   \tNAME : " + user.getName() + "\n" + "   \tGENDER: " + user.getGender() + "\n"
                + "   \tBRITHDATE : " + user.getBrithDate() + "\n" + "   \tOVERALLSCORE : " + user.getOverallScore() + "\n" + "   \tFAMILYSITUATUON : " + user.getFamilySituation());
        LocalDate date = LocalDate.now();
        if (user.getNgayNhan() != null && !user.getNgayNhan().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.getDefault());  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
            date = LocalDate.parse(user.getNgayNhan(), formatter);
        }
        ReceivedDate.setValue(date);
        textFieldMoney.setText(user.getSoTien());

        ObservableList<Organizetion> orranizetions = MysqlOrganizetion.getOrranizetions();
        comboBoxOrganizaName.getItems().addAll(orranizetions);
        comboBoxOrganizaName.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            selectedOrganization = newValue;
        });
        if (user.getTenToChuc() != null && !user.getTenToChuc().isEmpty()) {

            for (Organizetion o : orranizetions) {
                if (o.getTenToChuc().equals(user.getTenToChuc())) {
                    selectedOrganization = o;
                }
            }

            comboBoxOrganizaName.setValue(selectedOrganization);
        }

    }

    //chọn một học sinh nếu học sinh đó chưa có thông tin học bỗng thì vào hàm add_Users
//    ngược lại học sinh đó đã có thông tin học bỗng thì vào hàm Edit_Users
    public void addOrInsert() {
        if (user.getIdHocBong() == null || user.getIdHocBong().isEmpty()) {
            Add_Users();
        } else {
            Edit_Users(user.getIdHocBong());
        }
    }

    // Hàm thêm thông tin học bỗng (IDHocBong,IDSinhVien,IDToChuc, NGAYNHAN, SOTIEN)
    public void Add_Users() {

//        user.setSoTien(textFieldMoney.getText());
//        Connection conn = ConnectionUtil.connectdb();
//        String sql = "INSERT INTO THONGTINHOCBONG(IDHocBong,IDSinhVien,IDToChuc, NGAYNHAN, SOTIEN) VALUES(?,?,?,?,?);";
        try {
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, valueOf((int) (random() * 10000000)));
//            pst.setString(2, user.getMssv());
//            pst.setString(3, selectedOrganization.getId());
//            pst.setString(4, valueOf(ReceivedDate.getValue()));
//            pst.setString(5, textFieldMoney.getText());
//            pst.execute();
            CreateUsersStudentFormApi.createdataaaddusers(user.getMssv(), selectedOrganization.getId(),
                    valueOf(ReceivedDate.getValue()), Integer.parseInt(textFieldMoney.getText()));
            JOptionPane.showConfirmDialog(null, "user add succes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm sửa thông tin học bỗng ( NGAYNHAN, SOTIEN, IDToChuc)
    public void Edit_Users(String idHocBong) {

//        user.setSoTien(textFieldMoney.getText());
//        Connection conn = ConnectionUtil.connectdb();
//        String sql = "UPDATE THONGTINHOCBONG\n" +
//                "SET NGAYNHAN = ?, SOTIEN= ?, IDToChuc= ?\n" +
//                "WHERE IDHocBong = ?;\n";
        try {
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, valueOf(ReceivedDate.getValue()));
//            pst.setString(2, textFieldMoney.getText());
//            pst.setString(3, selectedOrganization.getId());
//            pst.setString(4, idHocBong);
//            pst.execute();
            UpdateUsersStudentFormApi.updateDataAddUsers(
                    idHocBong,
                    selectedOrganization.getId(),
                    valueOf(ReceivedDate.getValue()),
                    Integer.parseInt(textFieldMoney.getText())
            );
            JOptionPane.showConfirmDialog(null, "user edit succes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
