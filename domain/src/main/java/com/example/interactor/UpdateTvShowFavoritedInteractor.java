package com.example.interactor;

import com.example.entities.TvShow;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.TvShowRepository;

import javax.inject.Inject;

/**
 * Created by joela on 13/02/2017.
 */

public class UpdateTvShowFavoritedInteractor extends BaseUseCase<TvShow> implements Interactor<Void, Integer> {

    private Integer tvShowId;

    private final TvShowRepository tvShowRepository;
    private final ThreadExecutor executor;

    @Inject
    public UpdateTvShowFavoritedInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, TvShowRepository tvShowRepository) {
        super(postExecutionThread);
        this.executor = executor;
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public void run() {
        tvShowRepository.updateTvShowFavorited(tvShowId);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(R defaultCallback, Integer args) {
        this.tvShowId = args;
        executor.execute(this);
    }
}
