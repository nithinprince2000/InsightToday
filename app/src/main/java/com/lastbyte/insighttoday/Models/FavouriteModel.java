package com.lastbyte.insighttoday.Models;

import androidx.annotation.NonNull;

public class FavouriteModel extends WeatherModel {

    public FavouriteModel() {
    }

    public FavouriteModel(String city, String weather, double temperature, int icon) {
        super(city, weather, temperature, icon);
    }
}
