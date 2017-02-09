package com.example.data.realm.entities;

import com.example.data.realm.util.RealmString;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class TvShowRealm extends RealmObject{
    @Ignore
    public static final String TVSHOW_ID_REALM = "id";
    @Ignore
    public static final String TVSHOW_POPULARITY_REALM = "popularity";

    private String posterPath;
    private String overview;
    private RealmList<RealmString> genreIds;
    @PrimaryKey
    private Integer id;
    private String name;
    private float voteAverage;
    private boolean isFavorited;
    private float popularity;
    private long createdAt;

    public TvShowRealm() {
    }

    public TvShowRealm(int id, String name, String overview, float voteAverage,
                      RealmList<RealmString> genreIds, String posterPath,
                      boolean isFavorited, float popularity) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.genreIds = genreIds;
        this.id = id;
        this.name = name;
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



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
