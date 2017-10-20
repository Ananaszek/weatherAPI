package pl.agataanaszewicz.models;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    public static String makeHttpRequest(String url){
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            StringBuilder builder = new StringBuilder();
            InputStream inputStream = urlConnection.getInputStream();
            int read;
            while ((read = inputStream.read()) != -1){
                builder.append((char) read);
            }

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
