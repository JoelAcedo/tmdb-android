package com.jag.movies.Model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Albert Ruiz on 27/01/2017.
 */

public interface MovieService {

    //@GET("movie/top_rated")
    //Call<MovieList> getTopRatedMovies(@Query("api_key") String apiKey);

    //Gets page 1 by default
    @GET("discover/movie/")
    Call<MovieList> fetchMovies(@Query("api_key") String apiKey);

    @GET("movie/{movieId}")
    Call<MovieDTO> getMovieById(@Path("movieId") String movieId, @Query("api_key") String apiKey);

}
