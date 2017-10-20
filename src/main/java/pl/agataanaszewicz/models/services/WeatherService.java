package pl.agataanaszewicz.models.services;

import javafx.application.Platform;
import org.json.JSONObject;
import pl.agataanaszewicz.models.Config;
import pl.agataanaszewicz.models.IWeatherObserver;
import pl.agataanaszewicz.models.Utils;
import pl.agataanaszewicz.models.WeatherInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }
    private List<IWeatherObserver> observer = new ArrayList<>();
    private ExecutorService executorService;

    private WeatherService() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public void makeRequest(String city){
        Runnable runnable = () -> readJsonData(Utils.makeHttpRequest(Config.APP_BASE_URL + city + "&appid=" + Config.APP_ID), city);
        executorService.execute(runnable);
    }



    private void readJsonData(String json, String cityname){
        JSONObject root = new JSONObject(json);
        JSONObject main = root.getJSONObject("main");


        int pressure = main.getInt("pressure");
        double temp = main.getDouble("temp");

        observer.forEach(s -> {
            Platform.runLater(() -> s.onWeatherUpdate(new WeatherInfo(temp, pressure,cityname)));
        });
    }

    public void registerObserver(IWeatherObserver observer){
        this.observer.add(observer);
    }
}
