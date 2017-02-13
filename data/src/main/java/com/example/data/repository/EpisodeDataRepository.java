package com.example.data.repository;

import com.example.data.expception.DataErrorBundle;
import com.example.data.repository.datasource.episodes.CacheEpisodeDataSource;
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
    private final CacheEpisodeDataSource cacheEpisodeDataSource;

    @Inject
    public EpisodeDataRepository(ReadableEpisodeDataSource dataSource, CacheEpisodeDataSource cacheEpisodeDataSource) {
        this.dataSource = dataSource;
        this.cacheEpisodeDataSource = cacheEpisodeDataSource;
    }

    @Override
    public void getEpisodes(int tvShowId, int seasonNumber, GetEpisodesCallback callback) {
        try {
            List<Episode> episodes = cacheEpisodeDataSource.getEpisodes(tvShowId, seasonNumber);

            if (episodes.isEmpty()) {
                episodes = dataSource.getEpisodes(tvShowId, seasonNumber);
                callback.onSuccess(episodes);
                cacheEpisodeDataSource.saveEpisodes(episodes);
            } else {
                callback.onSuccess(episodes);
            }
        } catch (IOException e) {
            callback.onError(new DataErrorBundle(e));
        }
    }

}
