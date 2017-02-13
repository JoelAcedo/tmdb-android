package com.example.repositories;

import com.example.entities.TvShow;
import com.example.interactor.DefaultCallback;

import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public interface TvShowRepository {
    interface GetTvShowsCallback extends DefaultCallback<List<TvShow>> {}
    interface GetTvShowByIdCallback extends DefaultCallback<TvShow> {}

    void getTvShows(int page, GetTvShowsCallback callback);

    void getTvShowById(int tvShowId, GetTvShowByIdCallback callback);

    void updateTvShowFavorited(int tvShowId);
}
