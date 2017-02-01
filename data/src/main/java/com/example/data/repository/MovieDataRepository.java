package com.example.data.repository;

import com.example.data.repository.datasource.MovieDataSource;
import com.example.entities.Movie;
import com.example.repositories.MovieRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by inlab on 01/02/2017.
 */

public class MovieDataRepository implements MovieRepository {

    private final MovieDataSource dataSource;

    @Inject
    public MovieDataRepository(MovieDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void getMovies(GetMoviesCallback callback) {
        try {
            List<Movie> movies = dataSource.getMovies();
            callback.onSuccess(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getMovieById(int movieId, GetMovieByIdCallback callback) {
        try {
            Movie movies = dataSource.getMovieById(movieId);
            callback.onSuccess(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
