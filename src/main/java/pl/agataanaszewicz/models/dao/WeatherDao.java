package pl.agataanaszewicz.models.dao;

import pl.agataanaszewicz.models.WeatherModel;

import java.util.List;

public interface WeatherDao {
    void addWeather(WeatherModel model);
    List<WeatherModel> getAllWeatherData(String cityname);
    List<String> getCities();
}
