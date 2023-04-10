package xinhocbong.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import xinhocbong.users.Organizetion;
import xinhocbong.users.User;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class AddOrganizationControllerApi {

    private static Type listType = new TypeToken<ArrayList<Organizetion>>() {
    }.getType();

    public static List<Organizetion> saveDataOrganization(String id, String tenToChuc) {
        List<Organizetion> organizetion = new ArrayList<>();
        try {
            URL url = new URL((String.format("http://localhost:8080/setdataaddorganization?" +
                            "id=%s" +
                            "&tenToChuc=%s",
                    id, tenToChuc)));
            String responseStr = ConnectionApiUtil.callApi(url, "GET");
            Gson gson = new Gson();

            organizetion = gson.fromJson(responseStr, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return organizetion;
    }
}
