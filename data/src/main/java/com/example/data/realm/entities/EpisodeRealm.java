package com.example.data.realm.entities;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by joela on 13/02/2017.
 */

public class EpisodeRealm extends RealmObject {

    @Ignore
    public static final String EPISODE_ID_REALM = "id";

    @Ignore
    public static final String EPISODE_SEASON_NUMBER = "seasonNumber";

    @Ignore
    public static final String EPISODE_TVSHOW_ID = "tvShowId";

    private int id;
    private int episodeNumber;
    private String name;
    private String releaseDate;
    private String overview;
    private String posterPath;
    private int tvShowId;
    private int seasonNumber;

    public EpisodeRealm() {
    }

    public EpisodeRealm(int id, int episodeNumber, String name, String releaseDate, String overview, String posterPath, int tvShowId, int seasonNumber) {
        this.id = id;
        this.episodeNumber = episodeNumber;
        this.name = name;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.posterPath = posterPath;
        this.tvShowId = tvShowId;
        this.seasonNumber = seasonNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getTvShowId() {
        return tvShowId;
    }

    public void setTvShowId(int tvShowId) {
        this.tvShowId = tvShowId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
}
