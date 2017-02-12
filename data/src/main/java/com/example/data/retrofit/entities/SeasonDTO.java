package com.example.data.retrofit.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Albert.Ruiz on 12/02/2017.
 */

public class SeasonDTO {
    @SerializedName("episode_count")
    private int episodeCount;
    @SerializedName("season_number")
    private int seasonNumber;
    @SerializedName("poster_path")
    private int posterPath;

    public SeasonDTO(int episodeCount, int seasonNumber, int posterPath) {
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

    public int getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(int posterPath) {
        this.posterPath = posterPath;
    }
}
