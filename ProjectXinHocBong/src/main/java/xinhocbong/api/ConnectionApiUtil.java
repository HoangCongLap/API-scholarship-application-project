package xinhocbong.api;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConnectionApiUtil {

    public static String callApi(URL url, String method) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {

            StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            scanner.close();
            return informationString.toString();
        }

    }
}

