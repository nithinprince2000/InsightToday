package com.lastbyte.insighttoday.Factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.lastbyte.insighttoday.Models.FavouriteModel;

public class FavouriteModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    public FavouriteModelFactory(Application myApplication) {
        application = myApplication;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FavouriteModel(application);
    }
}
