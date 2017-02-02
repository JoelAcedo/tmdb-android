package com.example.data.repository.datasource;

import com.example.entities.Movie;

import java.io.IOException;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public interface MovieDataSource {

    List<Movie> getMovies() throws IOException;
    Movie getMovieById(int movieId) throws IOException;
}
