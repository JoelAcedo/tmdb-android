package com.example.data.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Albert.Ruiz on 29/01/2017.
 */
public class ApiClient {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public final static String API_KEY = "aaec0debce0a3fd90e4771eb5a266437";
    public final static String RESULTS_FOR_PAGE = "20";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
