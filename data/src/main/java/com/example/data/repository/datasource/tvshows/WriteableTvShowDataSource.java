package com.example.data.repository.datasource.tvshows;

import com.example.entities.TvShow;

import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public interface WriteableTvShowDataSource {
    void saveTvShows(List<TvShow> movies);

    void updateTvShowFavorited(int movieId);
}
