package com.lastbyte.insighttoday.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FavouriteRepository {
    FavouriteRoomDatabase favouriteRoomDatabase;
    FavouriteDao favouriteDao;
    private LiveData<List<Favourite>> listFavourite;

    public FavouriteRepository(Application application) {
        favouriteRoomDatabase = FavouriteRoomDatabase.getDatabase(application);
        favouriteDao = favouriteRoomDatabase.favouriteDao();
        listFavourite = favouriteDao.getFavourites();
    }

    public void addToFavourites(Favourite favourite) {
        FavouriteRoomDatabase.databaseWriteExecutor.execute(() -> favouriteDao.addToFavourites(favourite));
    }

    public void removeFavourite(Favourite favourite) {
        FavouriteRoomDatabase.databaseWriteExecutor.execute(() -> favouriteDao.removeFavourite(favourite));
    }

    public LiveData<List<Favourite>> getAllFavourites() {
        return listFavourite;
    }
}
