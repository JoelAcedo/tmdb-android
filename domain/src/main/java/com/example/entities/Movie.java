package com.example.entities;

import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public class Movie {
    private int movieId;
    private String title;
    private String overview;
    private float voteAverage;
    private String releaseDate;
    private List<String> genresList;
    private String coverUrl;
    private boolean isFavorited;
    private float popularity;

    public Movie(int movieId, String title, String overview, float voteAverage,
                 String releaseDate, List<String> genresList, String coverUrl,
                 boolean isFavorited, float popularity) {
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.genresList = genresList;
        this.coverUrl = coverUrl;
        this.isFavorited = isFavorited;
        this.popularity = popularity;
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

    public boolean isFavorited() {
        return isFavorited;
    }

    public float getPopularity() {
        return popularity;
    }
}
