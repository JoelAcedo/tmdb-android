package com.example.data.retrofit.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joela on 13/02/2017.
 */
public class EpisodeList {
    @SerializedName("episodes")
    private List<EpisodeDTO> episodes;

    public List<EpisodeDTO> getEpisodes() {
        return episodes;
    }
}
