package com.jag.movies.Model;


import java.io.IOException;
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

    private MovieList movieList;
    private int currentPage;

    @Inject
    private MovieService movieService;


    @Inject
    public DiscoverModel() {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", "97ec58875e6d00de8a5f19b9d272d3b1")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.movieService = retrofit.create(MovieService.class);
    }

    public List<MovieDTO> getMoviesList(int page) {
        this.currentPage = page;
        Call<MovieList> movieCall = movieService.fetchMovies("97ec58875e6d00de8a5f19b9d272d3b1", page);
        movieCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    movieList = response.body();
                    // tasks available
                } else {
                    //Log.e("", "onResponse: " + response.code());
                    // error response, ejemplo: 401
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
        return movieList.getMovies();
    }

    /*public MovieDTO getMoviebyIndex(int index) {
        movieList.getMovies().get(index);
    }*/

    public int getCurrentPage() {
        return currentPage;
    }
}
