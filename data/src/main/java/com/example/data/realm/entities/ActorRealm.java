package com.example.data.realm.entities;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by inlab on 02/02/2017.
 */

public class ActorRealm extends RealmObject {

    @Ignore
    public static final String MOVIE_ID_REALM = "movieId";

    private String character;
    private String name;
    private String profile_path;
    private int movieId;

    public ActorRealm() {
    }

    public ActorRealm(String name, String character, String profile_path, int movieId) {
        this.character = character;
        this.name = name;
        this.profile_path = profile_path;
        this.movieId = movieId;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
