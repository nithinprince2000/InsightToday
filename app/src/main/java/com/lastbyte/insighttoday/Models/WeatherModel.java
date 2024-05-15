package com.lastbyte.insighttoday.Models;

import androidx.annotation.NonNull;

public class WeatherModel {
    private String city;
    private String weather;
    private double temperature;
    private int icon;

    private double wind;
    private double rain;
    private double humidity;

    public WeatherModel() {
    }

    public WeatherModel(String city, String weather, double temperature, int icon) {
        this.city = city;
        this.weather = weather;
        this.temperature = temperature;
        this.icon = icon;
    }

    public WeatherModel(String city, String weather, double temperature, int icon, double wind, double rain, double humidity) {
        this.city = city;
        this.weather = weather;
        this.temperature = temperature;
        this.icon = icon;
        this.wind = wind;
        this.rain = rain;
        this.humidity = humidity;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
