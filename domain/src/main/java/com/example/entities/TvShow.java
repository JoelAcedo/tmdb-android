package com.example.entities;

import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class TvShow {
    private String overview;
    private String name;
    private String poster_path;
    private int id;
    private float voteAverage;
    private float popularity;
    private List<String> genresList;
    private boolean isFavorited;
    private int numberOfSeasons;
    private int numberOfEspisodes;

    public TvShow(String overview, String name, String poster_path, int id, float voteAverage, float popularity,
                  List<String> genresList, boolean isFavorited, int numberOfSeasons, int numberOfEspisodes) {
        this.overview = overview;
        this.name = name;
        this.poster_path = poster_path;
        this.id = id;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.genresList = genresList;
        this.isFavorited = isFavorited;
        this.numberOfEspisodes = numberOfEspisodes;
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public int getNumberOfEspisodes() {
        return numberOfEspisodes;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    public List<String> getGenresList() {
        return genresList;
    }

    public void setGenresList(List<String> genresList) {
        this.genresList = genresList;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getName() {
        return name;
    }

    public String getCoverUrl() {
        return poster_path;
    }
}
