package com.example.data.repository;

import android.util.Log;

import com.example.data.expception.DataErrorBundle;
import com.example.data.repository.datasource.movies.CacheMovieDataSource;
import com.example.data.repository.datasource.movies.ReadableMovieDataSource;
import com.example.entities.Movie;
import com.example.repositories.MovieRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by inlab on 01/02/2017.
 */

public class MovieDataRepository implements MovieRepository {

    public static final String TAG = "MovieDataRepository";
    private final ReadableMovieDataSource dataSource;
    private final CacheMovieDataSource cacheMovieDataSource;
    public final static int RESULTS_FOR_PAGE = 20;

    //Tiempo entre cada actualizacion de la bd mediante red, en milisegundos.
    private final static long TIME_BETWEEN_NETWORK_UPDATE = 3 * 60 * 60 * 1000;

    @Inject
    public MovieDataRepository(ReadableMovieDataSource dataSource, CacheMovieDataSource cacheMovieDataSource) {
        this.dataSource = dataSource;
        this.cacheMovieDataSource = cacheMovieDataSource;
    }

    @Override
    public void getMovies(int page, GetMoviesCallback callback) {
        try {
            List<Movie> movies;

            if (checkIfDoNetworkUpdate(page)) {
                updateFromNetwork(page, callback);
            } else {
                movies = cacheMovieDataSource.getMoviesByPage(page);

                if (movies.isEmpty() | movies.size() < RESULTS_FOR_PAGE) {
                    updateFromNetwork(page, callback);
                } else {
                    callback.onSuccess(movies);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(new DataErrorBundle(e));
        }
    }

    private void updateFromNetwork(int page, GetMoviesCallback callback) throws IOException {
        List<Movie> movies;
        // Para cargar los favoritos existentes debemos comprobar que pelis estan marcada en DB
        movies = dataSource.getMoviesByPage(page);
        cacheMovieDataSource.saveMovies(movies);
        movies = cacheMovieDataSource.getMoviesByPage(page);
        cacheMovieDataSource.setTimeFromLastUpdateCheck(page);
        callback.onSuccess(movies);
    }

    private boolean checkIfDoNetworkUpdate(int page) {
        Long timestampLastCheck = cacheMovieDataSource.getTimeFromLastUpdateCheck(page);
        if (System.currentTimeMillis() - TIME_BETWEEN_NETWORK_UPDATE > timestampLastCheck) {
//            Log.e(TAG, "Page: " + String.valueOf(page) + " need network update");
            return true;
        }
//        Log.e(TAG, "Page: " + String.valueOf(page) + " no network update");
        return false;
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

    @Override
    public void updateMovieFavorited(int movieId) {
        cacheMovieDataSource.updateMovieFavorited(movieId);
    }
}
