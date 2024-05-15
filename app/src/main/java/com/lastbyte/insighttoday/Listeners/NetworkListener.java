package com.lastbyte.insighttoday.Listeners;

import com.lastbyte.insighttoday.Models.WeatherModel;

public class NetworkListener {

    public interface Weather {
        void onWeatherResponse(WeatherModel weatherModel);
    }
}
