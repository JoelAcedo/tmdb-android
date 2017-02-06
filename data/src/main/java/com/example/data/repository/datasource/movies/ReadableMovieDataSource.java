package com.example.data.repository.datasource.movies;

import com.example.entities.Movie;

import java.io.IOException;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public interface ReadableMovieDataSource {

    List<Movie> getMoviesByPage(int page) throws IOException;
    Movie getMovieById(int movieId) throws IOException;
}
