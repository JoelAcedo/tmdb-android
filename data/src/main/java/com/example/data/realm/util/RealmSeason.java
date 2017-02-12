package com.example.data.realm.util;

import io.realm.RealmObject;

/**
 * Created by Albert.Ruiz on 12/02/2017.
 */

public class RealmSeason extends RealmObject {
    private int episodeCount;
    private int seasonNumber;
    private int posterPath;

    public RealmSeason() {
    }

    public RealmSeason(int episodeCount, int seasonNumber, int posterPath) {
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
