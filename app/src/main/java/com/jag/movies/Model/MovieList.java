package com.jag.movies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Albert Ruiz on 27/01/2017.
 */

public class MovieList {
    @SerializedName("page")
    int page;

    @SerializedName("results")
    List<MovieDTO> movies;

    @SerializedName("total_pages")
    int totalPages;

    @SerializedName("total_results")
    int totalResults;

    public int getPage() {
        return page;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
