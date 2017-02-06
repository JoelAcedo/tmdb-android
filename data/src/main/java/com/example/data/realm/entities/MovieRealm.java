package com.example.data.realm.entities;

import android.util.Log;

import com.example.data.realm.util.RealmString;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;


public class MovieRealm extends RealmObject {

    @Ignore
    public static final String MOVIE_ID_REALM = "id";
    @Ignore
    public static final String MOVIE_POPULARITY_REALM = "popularity";

    private String posterPath;
    private String overview;
    private String releaseDate;
    private RealmList<RealmString> genreIds;
    @PrimaryKey
    private Integer id;
    private String title;
    private float voteAverage;
    private boolean isFavorited;
    private float popularity;
    private long createdAt;

    public MovieRealm() {
    }

    public MovieRealm(int id, String title, String overview, float voteAverage,
                      String releaseDate, RealmList<RealmString> genreIds, String posterPath,
                      boolean isFavorited, float popularity) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.title = title;
        this.voteAverage = voteAverage;
        this.isFavorited = isFavorited;
        this.popularity = popularity;
        this.createdAt = System.currentTimeMillis();
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public RealmList<RealmString> getGenreIds() {
        return genreIds;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    public float getPopularity() {
        return popularity;
    }
}
