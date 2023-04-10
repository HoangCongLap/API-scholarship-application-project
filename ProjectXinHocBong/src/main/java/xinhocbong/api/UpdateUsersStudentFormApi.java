package xinhocbong.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import xinhocbong.users.Scholarship;

import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class UpdateUsersStudentFormApi {

    private static Type listType = new TypeToken<ArrayList<Scholarship>>() {
    }.getType();

    public static void updateDataAddUsers(String idHocBong,
                                          String idToChuc,
                                          String ngayNhan,
                                          Integer soTien) {
        try {
            URL url = new URL((String.format("http://localhost:8080/updatedataaaddusers?" +
                            "IDHocBong=%s" +
                            "&IDToChuc=%s" +
                            "&NGAYNHAN=%s" +
                            "&SOTIEN=%s",
                    idHocBong, idToChuc, ngayNhan, soTien)));
            ConnectionApiUtil.callApi(url, "GET");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
