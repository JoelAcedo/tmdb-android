package com.example.data.repository.datasource;

import com.example.data.entities.ActorDTO;
import com.example.entities.Actor;

import java.io.IOException;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */
public interface CastDataSource {

    List<Actor> getCastByMovieId(int movieId) throws IOException;
}
