package xinhocbong.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xinhocbong.api.entites.LoginResponse;
import xinhocbong.database.ConnectionUtil;
import xinhocbong.users.User;

import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllStudentInformationApi {
    private static Type listType = new TypeToken<ArrayList<User>>(){}.getType();

    public static List<User> getDataAllUsers() {
        List<User> users=new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/getdataallusers");
            String responseStr = ConnectionApiUtil.callApi(url, "GET");
            Gson gson = new Gson();

            users = gson.fromJson(responseStr, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
