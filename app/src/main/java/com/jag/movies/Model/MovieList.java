package com.jag.movies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Albert Ruiz on 27/01/2017.
 */

public class MovieList {
    @SerializedName("results")
    private List<MoviesDTO> results;

    public List<MoviesDTO> getResults() {
        return results;
    }
}
