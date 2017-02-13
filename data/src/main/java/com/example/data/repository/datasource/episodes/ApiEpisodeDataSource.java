package com.example.data.repository.datasource.episodes;

import com.example.data.mapper.EpisodeMapper;
import com.example.data.retrofit.ApiClient;
import com.example.data.retrofit.RetrofitService;
import com.example.data.retrofit.entities.EpisodeList;
import com.example.entities.Episode;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by joela on 13/02/2017.
 */

public class ApiEpisodeDataSource implements ReadableEpisodeDataSource {

    @Inject
    public ApiEpisodeDataSource() {
    }

    @Override
    public List<Episode> getEpisodes(int tvShowId, int season) throws IOException {
        Retrofit retrofit = ApiClient.getClient();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Response<EpisodeList> episodesResponse = retrofitService.getEpisodes(tvShowId, season,
                ApiClient.API_KEY).execute();

        return EpisodeMapper.fromEpisodeListDTO(episodesResponse.body().getEpisodes(), tvShowId, season);
    }
}
