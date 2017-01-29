package com.jag.movies.Model;


import android.util.Log;

import com.jag.movies.UI.MovieViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscoverModel {

    private final static String API_KEY = "aaec0debce0a3fd90e4771eb5a266437";
    private ArrayList<MovieViewModel> MOVIES;
    private int currentPage;



    @Inject
    public DiscoverModel() {
        this.MOVIES = new ArrayList<>();
        this.currentPage = 1;
    }

    public ArrayList<MovieViewModel> getData() {
        //this.currentPage = page;
        MovieService apiClient =
               ApiClient.getClient().create(MovieService.class);

        Call<MovieList> call = apiClient.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Log.e("onResponse", "Executing onResponse code");
                int statusCode = response.code();
                List<MovieDTO> movies_response = response.body().getResults();
                for (MovieDTO movie : movies_response) {
                    MOVIES.add(new MovieViewModel(movie.getId(),movie.getTitle(),
                            movie.getOverview(), movie.getVoteAverage(), movie.getReleaseDate(),
                            movie.getMovieGenres(), movie.getPosterPath()));
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Log error here since request failed
                Log.e("onFailure", t.toString());
            }
        });
        if (MOVIES == null || MOVIES.isEmpty()) {
            Log.e("NOT WORKING:", "movies list is empty");
        }
        return MOVIES;
    }

    /*public MovieDTO getMoviebyIndex(int index) {
        movieList.getMovies().get(index);
    }*/

    public int getCurrentPage() {
        return currentPage;
    }
}
