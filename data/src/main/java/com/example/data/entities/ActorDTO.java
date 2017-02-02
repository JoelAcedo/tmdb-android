package com.example.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inlab on 31/01/2017.
 */
public class ActorDTO {

    @SerializedName("character")
    private String character;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profile_path;

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
