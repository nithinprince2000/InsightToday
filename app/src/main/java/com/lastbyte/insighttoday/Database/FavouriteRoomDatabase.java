package com.lastbyte.insighttoday.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Favourite.class}, version = 1, exportSchema = false)
public abstract class FavouriteRoomDatabase extends RoomDatabase {
    public abstract FavouriteDao favouriteDao();

    private static volatile FavouriteRoomDatabase favouriteRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FavouriteRoomDatabase getDatabase(final Context context) {
        if (favouriteRoomDatabase == null) {
            synchronized (FavouriteRoomDatabase.class) {
                if (favouriteRoomDatabase == null) {
                    favouriteRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                    FavouriteRoomDatabase.class, "insight_today_database")
                            .build();
                }
            }
        }
        return favouriteRoomDatabase;
    }
}
