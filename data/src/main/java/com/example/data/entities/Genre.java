package com.example.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inlab on 30/01/2017.
 */

public class Genre {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String title;

    Genre(int id, String title) {
        this.id = id;
        this.title = title;
    }

    Genre() {}

    public int getId() {
        return id;
    }
}
