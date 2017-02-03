package com.example.data.realm.entities;

import com.example.data.realm.util.RealmString;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;


public class MovieRealm extends RealmObject {

    @Ignore
    public static final String MOVIE_ID_REALM = "id";

    private String posterPath;
    private String overview;
    private String releaseDate;
    private RealmList<RealmString> genreIds;
    private Integer id;
    private String title;
    private float voteAverage;

    public MovieRealm() {
    }

    public MovieRealm(int id, String title, String overview, float voteAverage,
                      String releaseDate, RealmList<RealmString> genreIds, String posterPath) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.title = title;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public RealmList<RealmString> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(RealmList<RealmString> genreIds) {
        this.genreIds = genreIds;
    }
}
