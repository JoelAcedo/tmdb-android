package com.example.data.repository;

import com.example.data.repository.datasource.CastDataSource;
import com.example.entities.Actor;
import com.example.repositories.CastRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public class CastDataRepository implements CastRepository {

    private final CastDataSource castDataSource;

    public CastDataRepository(CastDataSource castDataSource) {
        this.castDataSource = castDataSource;
    }

    @Override
    public void getCastByMovieId(int movieId, GetCastCallback callback) {
        try {
            List<Actor> movies = castDataSource.getCastByMovieId(movieId);
            callback.onSuccess(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
