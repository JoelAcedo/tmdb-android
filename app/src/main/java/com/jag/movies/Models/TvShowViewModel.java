package com.jag.movies.Models;

import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class TvShowViewModel {
    private int tvShowId;
    private String name;
    private String overview;
    private float voteAverage;
    private List<String> genresList;
    private String coverUrl;
    private boolean isFavorited;

    public TvShowViewModel(int tvShowId, String name, String overview, float voteAverage, List<String> genresList, String coverUrl, boolean isFavorited) {
        this.tvShowId = tvShowId;
        this.name = name;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.genresList = genresList;
        this.coverUrl = coverUrl;
        this.isFavorited = isFavorited;
    }

    public String getName() {
        return name;
    }

    public List<String> getGenresList() {
        return genresList;
    }

    public int getTvShowId() {
        return tvShowId;
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

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorited = isFavorite;
    }
}
