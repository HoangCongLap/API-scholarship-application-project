package xinhocbong.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import xinhocbong.database.ConnectionUtil;
import xinhocbong.function.StatisticalController;
import xinhocbong.users.User;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
import static xinhocbong.function.LoginController.showAlert;


public class AddStudentControllerApi {

    private static Type listType = new TypeToken<ArrayList<User>>() {
    }.getType();

    public static List<User> saveDataAddStudent(String mssv, String name, String gender, String brithDate,
                                                float overallScore, String familySituation) {
        List<User> users = new ArrayList<>();
        try {
            URL url = new URL((String.format("http://localhost:8080/setdataaddstudent?" +
                            "mssv=%s" +
                            "&name=%s" +
                            "&gender=%s" +
                            "&brithDate=%s" +
                            "&overallScore=%s" +
                            "&familySituation=%s",
                    mssv, name, gender, brithDate, overallScore, familySituation)));
            String responseStr = ConnectionApiUtil.callApi(url, "GET");
            Gson gson = new Gson();

            users = gson.fromJson(responseStr, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


}
