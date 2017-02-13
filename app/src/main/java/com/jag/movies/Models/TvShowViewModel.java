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
    private int numberOfSeasons;
    private int numberOfEpisodes;
    private List<SeasonViewModel> seasons;
    private String releaseDate;

    public TvShowViewModel(int tvShowId, String name, String overview, float voteAverage, List<String> genresList,
                           String coverUrl, boolean isFavorited, int numberOfSeasons, int numberOfEpisodes, List<SeasonViewModel> seasons,
                           String releaseDate) {
        this.tvShowId = tvShowId;
        this.name = name;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.genresList = genresList;
        this.coverUrl = coverUrl;
        this.isFavorited = isFavorited;
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
        this.seasons = seasons;
        this.releaseDate = releaseDate;
    }

    public List<SeasonViewModel> getSeasons() {
        return seasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
