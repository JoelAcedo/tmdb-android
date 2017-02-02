package com.example.data.repository;

import com.example.data.repository.datasource.CacheCastDataSource;
import com.example.data.repository.datasource.ReadableCastDataSource;
import com.example.entities.Actor;
import com.example.repositories.CastRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by inlab on 01/02/2017.
 */

public class CastDataRepository implements CastRepository {

    private final ReadableCastDataSource readableCastDataSource;
    private final CacheCastDataSource cacheCastDataSource;

    @Inject
    public CastDataRepository(ReadableCastDataSource readableCastDataSource, CacheCastDataSource cacheCastDataSource) {
        this.readableCastDataSource = readableCastDataSource;
        this.cacheCastDataSource = cacheCastDataSource;
    }

    @Override
    public void getCastByMovieId(int movieId, GetCastCallback callback) {
        try {
            //Get actors form Realm
            List<Actor> actors = cacheCastDataSource.getCastByMovieId(movieId);

            if (actors.isEmpty()) {
                // If data is not save at Realm DB, it is downloaded with retrofit and save.
                actors = readableCastDataSource.getCastByMovieId(movieId);
                callback.onSuccess(actors);
                cacheCastDataSource.saveCast(actors, movieId);
            } else {
                callback.onSuccess(actors);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
