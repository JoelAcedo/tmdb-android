package com.example.data.retrofit.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class TvShowList {
    @SerializedName("results")
    List<TvShowDTO> results;

    public List<TvShowDTO> getResults() {
        return results;
    }
}
