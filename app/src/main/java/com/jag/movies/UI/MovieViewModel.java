package com.jag.movies.UI;

import android.graphics.Bitmap;

import java.util.List;

public class MovieViewModel {
    private int movieId;
    private String title;
    private String overview;
    private float voteAverage;
    private String releaseDate;
    private List<String> genresList;
    private String coverUrl;

    public MovieViewModel(int movieId, String title, String overview, float voteAverage, String releaseDate, List<String> genresList, String coverUrl) {
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.genresList = genresList;
        this.coverUrl = coverUrl;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getOverview() {
        return overview;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
