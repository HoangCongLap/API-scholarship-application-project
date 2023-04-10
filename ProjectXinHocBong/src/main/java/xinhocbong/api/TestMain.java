package xinhocbong.api;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import xinhocbong.api.entites.LoginResponse;

public class TestMain {

    public static void main(String[] args) {
        try {

            URL url = new URL("http://localhost:8080/login?usename=lap&password=1212");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println("informationString:  " + informationString);
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(informationString.toString(), LoginResponse.class);

                System.out.println("loginResponse = " + loginResponse);
                //JSON simple library Setup with Maven is used to convert strings to JSON
//                JSONParser parse = new JSONParser();
//                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
//
//                //Get the first JSON object in the JSON array
//                System.out.println(dataObject.get(0));
//
//                JSONObject countryData = (JSONObject) dataObject.get(0);
//
//                System.out.println(countryData.get("woeid"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}