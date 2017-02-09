package com.example.data.repository.datasource.tvshows;

import com.example.data.mapper.TvShowMapper;
import com.example.data.retrofit.ApiClient;
import com.example.data.retrofit.RetrofitService;
import com.example.data.retrofit.entities.TvShowDTO;
import com.example.data.retrofit.entities.TvShowList;
import com.example.entities.TvShow;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class ApiTvShowDataSource implements ReadableTvShowDataSource{
    @Inject
    public ApiTvShowDataSource() {
    }

    @Override
    public List<TvShow> getTvShowsByPage(int page) throws IOException {
        //TODO injectar dagger
        Retrofit retrofit = ApiClient.getClient();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Response<TvShowList> tvShowsResponse = retrofitService.fetchTvShows(page, ApiClient.API_KEY).execute();

        List<TvShow> tvShows = TvShowMapper.fromTvShowListDTO(tvShowsResponse.body().getResults());
        return tvShows;
    }

    @Override
    public TvShow getTvShowById(int tvShowId) throws IOException {
        Retrofit retrofit = ApiClient.getClient();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Response<TvShowDTO> tvShowsResponse = retrofitService.getTvShowById(tvShowId, ApiClient.API_KEY).execute();

        TvShow tvShow = TvShowMapper.fromTvShowDTO(tvShowsResponse.body());
        return tvShow;

    }
}
