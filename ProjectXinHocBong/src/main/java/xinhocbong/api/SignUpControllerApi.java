package xinhocbong.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import xinhocbong.database.ConnectionUtil;
import xinhocbong.function.LoginController;
import xinhocbong.users.Organizetion;
import xinhocbong.users.SignUp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static xinhocbong.function.LoginController.showAlert;

public class SignUpControllerApi {

    private static Type listType = new TypeToken<ArrayList<SignUp>>() {
    }.getType();

    public static java.util.List<SignUp> getDataAllScholarshipUsers(String username, String password) {
        List<SignUp> signUps = new ArrayList<>();
        try {
            URL url = new URL((String.format("http://localhost:8080/setdataaddsignup?" +
                            "username=%s" +
                            "&password=%s",
                    username, password)));
            String responseStr = ConnectionApiUtil.callApi(url, "GET");
            Gson gson = new Gson();

            signUps = gson.fromJson(responseStr, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signUps;
    }
}
