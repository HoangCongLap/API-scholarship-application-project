package com.scholarship.restservice.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDatabase {
    public static boolean login(String userName, String pass) {
        String sql = "SELECT * FROM TAIKHOAN WHERE usename = ? and password = ?";
        try {
            PreparedStatement preparedStatement = ConnectionUtil.conn.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.first();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}