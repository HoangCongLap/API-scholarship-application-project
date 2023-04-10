package xinhocbong.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import xinhocbong.users.Scholarship;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class CreateUsersStudentFormApi {

    private static final Type listType = new TypeToken<ArrayList<Scholarship>>() {
    }.getType();

    public static void createdataaaddusers(String idSinhVien,
                                           String idToChuc,
                                           String ngayNhan,
                                           Integer soTien) {
        try {
            URL url = new URL((String.format("http://localhost:8080/createdataaaddusers?" +
                    "IDSinhVien=%s" + "&IDToChuc=%s" +
                    "&NGAYNHAN=%s" + "&SOTIEN=%s", idSinhVien, idToChuc, ngayNhan, soTien)));
            ConnectionApiUtil.callApi(url, "GET");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
