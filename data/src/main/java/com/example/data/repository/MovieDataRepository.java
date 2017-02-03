package com.example.data.repository;

import com.example.data.expception.DataErrorBundle;
import com.example.data.repository.datasource.movies.CacheMovieDataSource;
import com.example.data.repository.datasource.movies.ReadableMovieDataSource;
import com.example.data.repository.datasource.movies.WriteableMovieDataSource;
import com.example.entities.Movie;
import com.example.repositories.MovieRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by inlab on 01/02/2017.
 */

public class MovieDataRepository implements MovieRepository {

    private final ReadableMovieDataSource dataSource;
    private final CacheMovieDataSource cacheMovieDataSource;

    @Inject
    public MovieDataRepository(ReadableMovieDataSource dataSource, CacheMovieDataSource cacheMovieDataSource) {
        this.dataSource = dataSource;
        this.cacheMovieDataSource = cacheMovieDataSource;
    }

    @Override
    public void getMovies(GetMoviesCallback callback) {
        try {
            //Get movies form Realm
            List<Movie> movies = cacheMovieDataSource.getMovies();

            if (movies.isEmpty()) {
                // If data is not save at Realm DB, it is downloaded with retrofit and save.
                movies = dataSource.getMovies();
                callback.onSuccess(movies);
                cacheMovieDataSource.saveMovies(movies);
            } else {
                callback.onSuccess(movies);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(new DataErrorBundle(e));
        }
    }

    @Override
    public void getMovieById(int movieId, GetMovieByIdCallback callback) {
        try {
            //Get movies form Realm
            Movie movie = cacheMovieDataSource.getMovieById(movieId);

            if (movie == null) {
                // If data is not save at Realm DB, it is downloaded with retrofit and save.
                movie = dataSource.getMovieById(movieId);
                callback.onSuccess(movie);
            } else {
                callback.onSuccess(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(new DataErrorBundle(e));
        }
    }
}
