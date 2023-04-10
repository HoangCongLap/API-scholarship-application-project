package xinhocbong.function;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import xinhocbong.api.LoginApi;
import xinhocbong.database.LoginDatabase;


import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passWordTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public LoginController() {
    }

    private static final String LOGIN_XML_FILE = "Statistical.fxml";

    // kiểm tra userName,pass
    public void login(ActionEvent event) throws IOException {

        String userName = userNameTextField.getText();
        String pass = passWordTextField.getText();
//        userName = "HoangCongLap";
//        pass = "123";
//        String sql = "SELECT * FROM TAIKHOAN WHERE usename = ? and password = ?";
        try {
            boolean isLoginSuccess = LoginApi.login(userName, pass);
            if (isLoginSuccess) {
//                infoBox("Login Successfull", "Success", null);
// gọi sang Statistical.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(LOGIN_XML_FILE));
                root = loader.load();
                StatisticalController scene2Controller = loader.getController();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
// userName hoặcc pass sai
                showAlert("Wrong user and password", "Failed", null);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlert(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void signUpAtionLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SignUp.fxml"));
        root = loader.load();
        SignUpController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void cancelLogin(ActionEvent event) throws IOException {
                Platform.exit();
                System.exit(0);
    }

}
