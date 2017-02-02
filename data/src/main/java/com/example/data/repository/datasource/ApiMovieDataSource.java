package com.example.data.repository.datasource;

import com.example.data.retrofit.entities.MovieDTO;
import com.example.data.retrofit.entities.MovieList;
import com.example.data.mapper.MovieMapper;
import com.example.data.retrofit.ApiClient;
import com.example.data.retrofit.MovieService;
import com.example.entities.Movie;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by inlab on 01/02/2017.
 */

public class ApiMovieDataSource implements MovieDataSource {

    @Inject
    public ApiMovieDataSource() {
    }

    @Override
    public List<Movie> getMovies() throws IOException {
        //TODO injectar dagger
        Retrofit retrofit = ApiClient.getClient();

        MovieService movieService = retrofit.create(MovieService.class);
        Response<MovieList> moviesResponse = movieService.fetchMovies(ApiClient.API_KEY).execute();

        List<Movie> movies = MovieMapper.fromMovieListDTO(moviesResponse.body().getResults());
        return movies;
    }

    @Override
    public Movie getMovieById(int movieId) throws IOException {
        Retrofit retrofit = ApiClient.getClient();

        MovieService movieService = retrofit.create(MovieService.class);
        Response<MovieDTO> movieResponse = movieService.getMovieById(String.valueOf(movieId), ApiClient.API_KEY).execute();

        Movie movie = MovieMapper.fromMovieDTO(movieResponse.body());
        return movie;
    }
}
