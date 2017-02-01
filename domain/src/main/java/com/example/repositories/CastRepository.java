package com.example.repositories;

import com.example.entities.Actor;
import com.example.interactor.DefaultCallback;

import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public interface CastRepository {

    interface GetCastCallback extends DefaultCallback<List<Actor>> {}

    void getCastByMovieId(int movieId, GetCastCallback callback);
}
