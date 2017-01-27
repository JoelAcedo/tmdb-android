package com.jag.movies.Presenter;

import android.util.Log;

import com.jag.movies.Model.DiscoverModel;
import com.jag.movies.Model.MovieDTO;
import com.jag.movies.Model.MovieList;
import com.jag.movies.Model.MovieService;
import com.jag.movies.UI.DiscoverView;
import com.jag.movies.UI.IDiscoverView;

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


public class DiscoverPresenter {

    private final IDiscoverView discoverView;
    private final DiscoverModel discoverModel;



    @Inject
    public DiscoverPresenter(IDiscoverView discoverView, DiscoverModel discoverModel) {
        this.discoverView = discoverView;
        this.discoverModel = discoverModel;
    }





}
