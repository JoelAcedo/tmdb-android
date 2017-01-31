package com.jag.movies.Model;

import android.util.Log;

import com.jag.movies.Callbacks.ActorListCallback;
import com.jag.movies.Callbacks.MovieCallback;
import com.jag.movies.Retrofit.ActorDTO;
import com.jag.movies.Retrofit.ActorList;
import com.jag.movies.Retrofit.ApiClient;
import com.jag.movies.Retrofit.MovieDTO;
import com.jag.movies.Retrofit.MovieService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailModel {
    private final static String API_KEY = "aaec0debce0a3fd90e4771eb5a266437";
    private final static String TAG = "DetailModel";

    @Inject
    public DetailModel() {
    }

    public void getMovieByIndex(int movieId, final MovieCallback callback) {
        MovieService apiClient =
                ApiClient.getClient().create(MovieService.class);

        Call<MovieDTO> call = apiClient.getMovieById(String.valueOf(movieId), API_KEY);
        call.enqueue(new Callback<MovieDTO>() {
            @Override
            public void onResponse(Call<MovieDTO> call, Response<MovieDTO> response) {

                if (response.isSuccessful()) {
                    MovieDTO movie = response.body();
                    callback.movieMapper(movie);
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieDTO> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "onFailure" + t.toString());
            }
        });
    }

    public void getCastByMovieId(int movieId, final ActorListCallback callback) {
        MovieService apiClient =
                ApiClient.getClient().create(MovieService.class);

        Call<ActorList> call = apiClient.getCastByMovieId(String.valueOf(movieId), API_KEY);

        call.enqueue(new Callback<ActorList>() {
            @Override
            public void onResponse(Call<ActorList> call, Response<ActorList> response) {

                if (response.isSuccessful()) {
                    List<ActorDTO> actors = response.body().getCast();
                    callback.dataReady(((ArrayList<ActorDTO>) actors));
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ActorList> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "onFailure" + t.toString());
            }
        });
    }


}
