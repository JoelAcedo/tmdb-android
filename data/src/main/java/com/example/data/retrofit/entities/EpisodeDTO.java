package com.example.data.retrofit.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joela on 13/02/2017.
 */
public class EpisodeDTO {

    @SerializedName("id")
    private int id;
    @SerializedName("episode_number")
    private int episodeNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("air_date")
    private String releaseDate;
    @SerializedName("overview")
    private String overview;
    @SerializedName("still_path")
    private String posterPath;

    public int getId() {
        return id;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
