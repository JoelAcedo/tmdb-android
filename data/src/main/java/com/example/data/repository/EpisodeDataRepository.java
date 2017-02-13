package com.example.data.repository;

import com.example.data.expception.DataErrorBundle;
import com.example.data.repository.datasource.episodes.ReadableEpisodeDataSource;
import com.example.entities.Episode;
import com.example.repositories.EpisodeRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by joela on 13/02/2017.
 */

public class EpisodeDataRepository implements EpisodeRepository {

    public static final String TAG = "MovieDataRepository";
    private final ReadableEpisodeDataSource dataSource;

    @Inject
    public EpisodeDataRepository(ReadableEpisodeDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void getEpisodes(int tvShowId, int seasonNumber, GetEpisodesCallback callback) {
        try {
            List<Episode> episodes = dataSource.getEpisodes(tvShowId, seasonNumber);
            callback.onSuccess(episodes);
        } catch (IOException e) {
            callback.onError(new DataErrorBundle(e));
        }
    }

}
