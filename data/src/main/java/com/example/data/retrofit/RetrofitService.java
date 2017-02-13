package com.example.data.retrofit;


import com.example.data.retrofit.entities.ActorList;
import com.example.data.retrofit.entities.EpisodeList;
import com.example.data.retrofit.entities.MovieDTO;
import com.example.data.retrofit.entities.MovieList;
import com.example.data.retrofit.entities.TvShowDTO;
import com.example.data.retrofit.entities.TvShowList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Albert Ruiz on 27/01/2017.
 */

public interface RetrofitService {

    //@GET("movie/top_rated")
    //Call<MovieList> getTopRatedMovies(@Query("api_key") String apiKey);

    //Gets page 1 by default
    @GET("discover/movie/")
    Call<MovieList> fetchMovies(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("movie/{movieId}")
    Call<MovieDTO> getMovieById(@Path("movieId") String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movieId}/credits")
    Call<ActorList> getCastByMovieId(@Path("movieId") String movieId, @Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShowList> fetchTvShows(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}")
    Call<TvShowDTO> getTvShowById(@Path("tv_id") int id, @Query("api_key")String apiKey);

    @GET("tv/{tv_id}/season/{season_number}")
    Call<EpisodeList> getEpisodes(@Path("tv_id") int tvShowId, @Path("season_number") int seasonNumber,
                                  @Query("api_key") String apiKey);

}
