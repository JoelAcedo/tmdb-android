package com.example.data.retrofit.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Albert Ruiz on 27/01/2017.
 */

public class MovieList {
    @SerializedName("results")
    private List<MovieDTO> results;

    public List<MovieDTO> getResults() {
        return results;
    }
}
