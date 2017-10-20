package pl.agataanaszewicz.models;

public interface IWeatherObserver {
    void onWeatherUpdate(WeatherInfo info);
}
