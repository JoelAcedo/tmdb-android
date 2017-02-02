package com.example.entities;

/**
 * Created by inlab on 01/02/2017.
 */

public class Actor {
    private String character;
    private String name;
    private String profileUrl;

    public Actor(String name, String character, String profileUrl) {
        this.name = name;
        this.character = character;
        this.profileUrl = profileUrl;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}

