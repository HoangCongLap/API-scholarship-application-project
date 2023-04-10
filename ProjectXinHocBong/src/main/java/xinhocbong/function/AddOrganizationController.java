package xinhocbong.function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import xinhocbong.api.AddOrganizationControllerApi;
import xinhocbong.api.AddOrganizationControllerApi;
import xinhocbong.database.ConnectionUtil;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static xinhocbong.function.LoginController.showAlert;

public class AddOrganizationController implements Initializable {
    @FXML
    private TextField idUp;
    @FXML
    private TextField ngayNhan;
    @FXML
    private TextField toChucChosoTien;
    @FXML
    private TextField toChuc;


    @FXML
    protected void IdUpDateAction(ActionEvent event) {
        String IdUp = idUp.getText();
    }

    @FXML
    protected void ngayNhanUpDateAction(ActionEvent event) {
        String NgayNhan = ngayNhan.getText();
    }

    @FXML
    protected void soTienUpDateAction(ActionEvent event) {
        String ToChucChoSoTienUp = toChucChosoTien.getText();
    }

    @FXML
    protected void toChucUpDateAction(ActionEvent event) {
        String ToChuc = toChuc.getText();
    }


    @FXML
    private TextField text_IdOrganization;
    @FXML
    private TextField text_toChucOrganization;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void exitAddOrganization(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Statistical.fxml"));
        root = loader.load();
        StatisticalController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void add_organization() {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "INSERT INTO TOCHUC(ID, TENTOCHUC) VALUES(?,?);\n";
        try {
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, text_IdOrganization.getText());
//            pst.setString(2, text_toChucOrganization.getText());

            if (text_IdOrganization.getText().equals("") || text_toChucOrganization.getText().equals("")) {
                check();
                showAlert("You missed some field!\n", "", null);
            } else {
                JOptionPane.showMessageDialog(null, "user addOrganization succes");
                AddOrganizationControllerApi.saveDataOrganization(text_IdOrganization.getText(), text_toChucOrganization.getText());
            }
//
//            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void check() {
        if (text_IdOrganization.getText().equals("")) {
            text_IdOrganization.setStyle("-fx-border-color: red;");
            text_IdOrganization.requestFocus();

        } else {
            text_IdOrganization.setStyle("-fx-border-color: #CFCFCF;");
        }

        if (text_toChucOrganization.getText().equals("")) {
            text_toChucOrganization.setStyle("-fx-border-color: red;");
            text_toChucOrganization.requestFocus();

        } else {
            text_toChucOrganization.setStyle("-fx-border-color: #CFCFCF;");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
//    @FXML
//    public void Update_student() {
//        Connection conn = ConnectionUtil.connectdb();
//        String sql = "INSERT INTO SINHVIEN(IDMSSV, NAME, GENDER, BRITHDATE, OVERALLSCORE, FAMILYSITUATION) VALUES(?,?,?,?,?,?);\n";
//        try {
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, txt_mssv.getText());
//            pst.setString(2, txt_name.getText());
//            pst.setString(3, combobox_gioiTinh.getValue());
//            pst.setString(4, valueOf(DatePicker_ngaySinh.getValue()));
//            pst.setString(5, txt_diem.getText());
//            pst.setString(6, txt_hoanCanh.getText());
//            pst.execute();
//            JOptionPane.showConfirmDialog(null, "user add succes");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
}
