package com.lastbyte.insighttoday.Singletons;

import com.lastbyte.insighttoday.Listeners.WeatherChangeListener;
import com.lastbyte.insighttoday.Models.WeatherModel;

public class InsightToday {

    private static InsightToday instance;

    private String location;
    private WeatherModel weatherData;
    private WeatherChangeListener weatherChangeListener;

    private InsightToday() {
    }

    public static InsightToday getInstance() {
        if (instance == null)
            instance = new InsightToday();
        return instance;
    }

    public WeatherModel getWeatherData() {
        return instance.weatherData;
    }

    public void setWeatherData(WeatherModel weatherData) {
        instance.weatherData = weatherData;
        if (instance.weatherChangeListener != null)
            instance.weatherChangeListener.onWeatherChanged(weatherData);
    }

    public void setWeatherChangeListener(WeatherChangeListener weatherChangeListener) {
        instance.weatherChangeListener = weatherChangeListener;
        if (instance.weatherData != null)
            instance.weatherChangeListener.onWeatherChanged(weatherData);
    }

    public void removeWeatherChangeListener() {
        instance.weatherChangeListener = null;
    }

    public String getLocation() {
        return instance.location;
    }

    public void setLocation(String location) {
        instance.location = location;
    }
}
