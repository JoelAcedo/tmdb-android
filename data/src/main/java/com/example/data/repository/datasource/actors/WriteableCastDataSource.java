package com.example.data.repository.datasource.actors;

import com.example.entities.Actor;

import java.util.List;

/**
 * Created by inlab on 02/02/2017.
 */

public interface WriteableCastDataSource  {

    void saveCast(List<Actor> actors, int movieId);
}
