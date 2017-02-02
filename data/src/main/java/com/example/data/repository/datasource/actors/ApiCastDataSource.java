package com.example.data.repository.datasource.actors;

import com.example.data.retrofit.entities.ActorList;
import com.example.data.mapper.CastMapper;
import com.example.data.retrofit.ApiClient;
import com.example.data.retrofit.RetrofitService;
import com.example.entities.Actor;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by inlab on 01/02/2017.
 */

public class ApiCastDataSource implements ReadableCastDataSource {

    @Inject
    public ApiCastDataSource() {
    }

    @Override
    public List<Actor> getCastByMovieId(int movieId) throws IOException {
        Retrofit retrofit = ApiClient.getClient();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Response<ActorList> moviesResponse = retrofitService.getCastByMovieId(String.valueOf(movieId), ApiClient.API_KEY).execute();

        return CastMapper.fromActorListDTO(moviesResponse.body().getCast());
    }
}
