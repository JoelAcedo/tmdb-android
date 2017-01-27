package com.jag.movies.Model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Albert Ruiz on 27/01/2017.
 */

public interface MovieService {

    //Gets page 1 by default
    @GET("discover/movie/")
    Call<MovieList> fetchMovies(@Query("api_key") String apiKey);

    @GET("discover/movie/")
    Call<MovieList> fetchMovies(@Query("api_key") String apiKey, @Query("page") int page);

}
