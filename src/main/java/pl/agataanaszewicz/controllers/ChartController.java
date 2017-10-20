package pl.agataanaszewicz.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import pl.agataanaszewicz.models.WeatherModel;
import pl.agataanaszewicz.models.dao.WeatherDao;
import pl.agataanaszewicz.models.dao.impl.WeatherDaoImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChartController implements Initializable{

    @FXML
    BarChart chartTemp;

    @FXML
    ListView<String> listCities;
    private ObservableList<String> cityObservableList;

    private WeatherDao weatherDao = new WeatherDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityObservableList = FXCollections.observableList(weatherDao.getCities());
        listCities.setItems(cityObservableList);

        listCities.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> generateChart(newValue)
        );

    }

    private void generateChart(String cityname) {
        List<WeatherModel> weatherList = weatherDao.getAllWeatherData(cityname);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(cityname);

        for (WeatherModel weatherModel : weatherList) {
            series.getData().add(new XYChart.Data<>(weatherModel.getDate().toString(), weatherModel.getTemp()-273));
        }

        chartTemp.getData().clear();
        chartTemp.getData().add(series);

    }
}
