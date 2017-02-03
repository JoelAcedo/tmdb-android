package com.example.data.repository.datasource.actors;

import com.example.entities.Actor;

import java.io.IOException;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */
public interface ReadableCastDataSource {

    List<Actor> getCastByMovieId(int movieId) throws IOException;
}
