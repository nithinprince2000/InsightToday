package com.lastbyte.insighttoday.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addToFavourites(Favourite student);

    @Query("SELECT * from favourite ORDER By city Asc")
    LiveData<List<Favourite>> getFavourites();

    @Query("DELETE from favourite")
    void deleteAllFavourites();

    @Delete
    void removeFavourite(Favourite favourite);
}
