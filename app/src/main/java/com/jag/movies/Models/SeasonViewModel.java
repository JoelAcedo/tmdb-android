package com.jag.movies.Models;

/**
 * Created by Albert.Ruiz on 12/02/2017.
 */

public class SeasonViewModel {
    private int episodeCount;
    private int seasonNumber;
    private String posterPath;

    public SeasonViewModel(int episodeCount, int seasonNumber, String posterPath) {
        this.episodeCount = episodeCount;
        this.seasonNumber = seasonNumber;
        this.posterPath = posterPath;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
