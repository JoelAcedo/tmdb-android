package com.example.data.repository.datasource.movies;

import com.example.entities.Movie;

import java.util.List;

/**
 * Created by inlab on 02/02/2017.
 */

public interface WriteableMovieDataSource {

    void saveMovies(List<Movie> movies);
}
