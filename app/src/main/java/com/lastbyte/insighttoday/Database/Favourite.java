package com.lastbyte.insighttoday.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite")
public class Favourite {
    @PrimaryKey
    @NonNull
    String city;

    public Favourite(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getCity() {
        return city;
    }
}
