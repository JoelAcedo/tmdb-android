package com.jag.movies.UI;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by inlab on 27/01/2017.
 */

public class MovieViewModel {
    int movieId;
    String title;
    String overview;
    float voteAverage;
    String releaseDate;
    List<String> genresList;
    Bitmap cover;


    public Bitmap getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenresList() {
        return genresList;
    }

    public int getMovieId() {
        return movieId;
    }
}
