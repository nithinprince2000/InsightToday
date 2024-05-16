package com.lastbyte.insighttoday.Models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lastbyte.insighttoday.Database.Favourite;
import com.lastbyte.insighttoday.Database.FavouriteRepository;

import java.util.List;

public class FavouriteModel extends AndroidViewModel {

    private FavouriteRepository favouriteRepository;
    private final LiveData<List<Favourite>> listLiveData;
    public FavouriteModel(@NonNull Application application) {
        super(application);
        favouriteRepository = new FavouriteRepository(application);
        listLiveData = favouriteRepository.getAllFavourites();
    }

    public LiveData<List<Favourite>> getAllFavourites() {
        return listLiveData;
    }

    public void addToFavourites(Favourite favourite) {
        favouriteRepository.addToFavourites(favourite);
    }

    public void removeFavourite(Favourite favourite) {
        favouriteRepository.removeFavourite(favourite);
    }
}
