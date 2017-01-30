package com.jag.movies.Model;


import android.util.Log;

import com.jag.movies.Callbacks.MovieListCallback;
import com.jag.movies.Retrofit.ApiClient;
import com.jag.movies.Retrofit.MovieList;
import com.jag.movies.Retrofit.MovieService;
import com.jag.movies.Retrofit.MoviesDTO;
import com.jag.movies.UI.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverModel {

    private final static String TAG = "DiscoverModel";
    private final static String API_KEY = "aaec0debce0a3fd90e4771eb5a266437";
    private ArrayList<MovieViewModel> movies;
    private int currentPage;



    @Inject
    public DiscoverModel() {
        this.movies = new ArrayList<>();
        this.currentPage = 1;
    }

    public void getData(final MovieListCallback callback) {
        MovieService apiClient =
               ApiClient.getClient().create(MovieService.class);

        Call<MovieList> call = apiClient.fetchMovies(API_KEY);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                if (response.isSuccessful()) {
                    //Log.e(TAG, "onResponse: Executing onResponse code");
                    List<MoviesDTO> movies_response = response.body().getResults();
                    for (MoviesDTO movie : movies_response) {
                        movies.add(new MovieViewModel(movie.getId(),movie.getTitle(),
                                movie.getOverview(), movie.getVoteAverage(), movie.getReleaseDate(),
                                movie.getMovieGenres(), "http://image.tmdb.org/t/p/w600" + movie.getPosterPath()));
                    }
                    callback.dataReady(movies);
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "onFailure" + t.toString());
            }
        });
    }

//    public int getCurrentPage() {
//        return currentPage;
//    }
}
