package com.jag.movies.Models;

/**
 * Created by Albert.Ruiz on 31/01/2017.
 */
public class ActorViewModel {
    private String character;
    private String name;
    private String profileUrl;

    public ActorViewModel(String name, String character, String profileUrl) {
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
