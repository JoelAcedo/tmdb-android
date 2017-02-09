package com.example.data.repository.datasource.tvshows;

import com.example.entities.TvShow;

import java.io.IOException;
import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public interface ReadableTvShowDataSource {
    List<TvShow> getTvShowsByPage(int page) throws IOException;
    TvShow getTvShowById(int tvShowId) throws IOException;
}
