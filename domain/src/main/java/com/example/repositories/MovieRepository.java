package com.example.repositories;

import com.example.entities.Movie;
import com.example.interactor.DefaultCallback;

import java.util.List;

public interface MovieRepository {

    interface GetMoviesCallback extends DefaultCallback<List<Movie>> {}
    interface GetMovieByIdCallback extends DefaultCallback<Movie> {}

    void getMovies(GetMoviesCallback callback);

    void getMovieById(int movieId, GetMovieByIdCallback callback);
}
