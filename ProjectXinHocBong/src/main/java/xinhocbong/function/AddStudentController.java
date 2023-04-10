package xinhocbong.function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import xinhocbong.api.AddStudentControllerApi;
import xinhocbong.database.ConnectionUtil;
import xinhocbong.users.User;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static xinhocbong.function.LoginController.showAlert;


public class AddStudentController implements Initializable {

    @FXML
    private DatePicker DatePicker_ngaySinh;


    @FXML
    private TextField txt_diem;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_hoanCanh;

    @FXML
    private TextField txt_mssv;

    @FXML
    private ComboBox<String> combobox_gioiTinh;
    private String[] list = {"Nam", "Nu"};

    private User user;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void Add_student() {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "INSERT INTO SINHVIEN(IDMSSV, NAME, GENDER, BRITHDATE, OVERALLSCORE, FAMILYSITUATION) VALUES(?,?,?,?,?,?);\n";
        try {
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, txt_mssv.getText());
//            pst.setString(2, txt_name.getText());
//            pst.setString(3, combobox_gioiTinh.getValue());
//            pst.setString(4, valueOf(DatePicker_ngaySinh.getValue()));
//            pst.setString(5, txt_diem.getText());
//            pst.setString(6, txt_hoanCanh.getText());

            //kiểm tra đã nhập thông tin đầy đủ chưa

            if (txt_mssv.getText().equals("") || txt_name.getText().equals("") || combobox_gioiTinh == null
                    || DatePicker_ngaySinh == null || txt_diem.getText().equals("") || txt_hoanCanh.getText().equals("")) {
                check();
                showAlert("You missed some field!\n", "", null);
            } else {
                AddStudentControllerApi.saveDataAddStudent(txt_mssv.getText(), txt_name.getText(), combobox_gioiTinh.getValue(),
                        valueOf(DatePicker_ngaySinh.getValue()), Float.parseFloat(txt_diem.getText()), txt_hoanCanh.getText());
                JOptionPane.showMessageDialog(null, "user addStudent succes");

            }
//            pst.execute();

        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox_gioiTinh.getItems().addAll(list);
        combobox_gioiTinh.setValue("Nam");
        DatePicker_ngaySinh.setValue(LocalDate.now());

    }

    @FXML
    public void exitAddStudent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Statistical.fxml"));
        root = loader.load();
        StatisticalController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void check() {
        if (txt_mssv.getText().equals("")) {
            txt_mssv.setStyle("-fx-border-color: red;");
//            showAlert("Mssv is required!\n", "", null);
            txt_mssv.requestFocus();

        } else {
            txt_mssv.setStyle("-fx-border-color: #CFCFCF;");
        }

        if (txt_name.getText().equals("")) {
            txt_name.setStyle("-fx-border-color: red;");
//            showAlert("Name is required!\n", "", null);
            txt_name.requestFocus();

        } else {
            txt_name.setStyle("-fx-border-color: #CFCFCF;");
        }

        if (combobox_gioiTinh.equals("")) {
            combobox_gioiTinh.setStyle("-fx-border-color: red;");
//            showAlert("Gender is required!\n", "", null);
            combobox_gioiTinh.requestFocus();

        } else {
            combobox_gioiTinh.setStyle("-fx-border-color: #CFCFCF;");
        }

        if (DatePicker_ngaySinh.equals(null)) {
            DatePicker_ngaySinh.setStyle("-fx-border-color: red;");
//            showAlert("BrithDate is required!\n", "", null);
            DatePicker_ngaySinh.requestFocus();

        } else {
            DatePicker_ngaySinh.setStyle("-fx-border-color: #CFCFCF;");
        }

        if (txt_diem.getText().equals("")) {
            txt_diem.setStyle("-fx-border-color: red;");
//            showAlert("OverallScore is required!\n", "", null);
            txt_diem.requestFocus();

        } else {
            txt_diem.setStyle("-fx-border-color: #CFCFCF;");
        }

        if (txt_hoanCanh.getText().equals("")) {
            txt_hoanCanh.setStyle("-fx-border-color: red;");
//            showAlert("FamilySituation is required!\n", "", null);
            txt_hoanCanh.requestFocus();

        } else {
            txt_hoanCanh.setStyle("-fx-border-color: #CFCFCF;");
        }

    }


}
