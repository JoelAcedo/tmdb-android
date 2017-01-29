package com.jag.movies.Model;


import android.util.Log;

import com.jag.movies.UI.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverModel {

    private final static String API_KEY = "aaec0debce0a3fd90e4771eb5a266437";
    private ArrayList<MovieViewModel> MOVIES;
    private int currentPage;



    @Inject
    public DiscoverModel() {
        this.MOVIES = new ArrayList<>();
        this.currentPage = 1;
        /*OkHttpClient.Builder httpClient =
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

        this.movieService = retrofit.create(MovieService.class);*/
    }

    public ArrayList<MovieViewModel> getData() {
        //this.currentPage = page;
        MovieService apiClient =
               ApiClient.getClient().create(MovieService.class);

        Call<MovieList> call = apiClient.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
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
            Log.e("FAIL: --->", "no funciona");
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
