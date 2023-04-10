package xinhocbong.api;

import com.google.gson.Gson;
import xinhocbong.api.entites.LoginResponse;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginApi {
    public static boolean login(String userName, String pass) {
        try {
            URL url = new URL(String.format("http://localhost:8080/login?username=%s&password=%s", userName, pass));
            String responseStr = ConnectionApiUtil.callApi(url, "GET");
            Gson gson = new Gson();
            LoginResponse loginResponse = gson.fromJson(responseStr, LoginResponse.class);
            return loginResponse.loginSuccess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}