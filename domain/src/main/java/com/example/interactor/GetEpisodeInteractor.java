package com.example.interactor;

import com.example.entities.Episode;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.EpisodeRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joela on 13/02/2017.
 */

public class GetEpisodeInteractor extends BaseUseCase<List<Episode>> implements Interactor<List<Episode>, Integer[]> {

    public static final int TVSHOW_ID = 0;
    public static final int SEASON_NUMBER = 1;


    private final EpisodeRepository episodeRepository;
    private final ThreadExecutor executor;
    private EpisodeRepository.GetEpisodesCallback callback;
    private Integer tvShowId;
    private Integer seasonNumber;

    private EpisodeRepository.GetEpisodesCallback dataCallback = new EpisodeRepository.GetEpisodesCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(List<Episode> returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    @Inject
    public GetEpisodeInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, EpisodeRepository episodeRepository) {
        super(postExecutionThread);
        this.executor = executor;
        this.episodeRepository = episodeRepository;
    }

    @Override
    public void run() {
        episodeRepository.getEpisodes(tvShowId, seasonNumber, dataCallback);
    }


    /* args = { tvShowId, seasonNumber} */
    @Override
    public <R extends DefaultCallback<List<Episode>>> void execute(R defaultCallback, Integer[] args) {
        this.callback = ((EpisodeRepository.GetEpisodesCallback) defaultCallback);
        this.tvShowId = args[TVSHOW_ID];
        this.seasonNumber = args[SEASON_NUMBER];
        executor.execute(this);
    }
}
