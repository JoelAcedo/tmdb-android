package com.example.data.retrofit.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by inlab on 31/01/2017.
 */
public class ActorList {
    @SerializedName("cast")
    private List<ActorDTO> cast;

    public List<ActorDTO> getCast() {
        return cast;
    }
}
