package pl.agataanaszewicz.models;

import java.sql.Date;

public class WeatherModel {
    private String cityname;
    private float temp;
    private Date date;

    public WeatherModel(String cityname, float temp, Date date) {
        this.cityname = cityname;
        this.temp = temp;
        this.date = date;
    }

    public WeatherModel(WeatherInfo info){
        this.cityname = info.getCityname();
        this.temp = (float) info.getTemp();
        this.date = new Date(0);
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
