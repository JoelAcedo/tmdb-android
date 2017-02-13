package com.example.interactor;

import com.example.entities.TvShow;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.TvShowRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joela on 12/02/2017.
 */

public class GetTvShowListInteractor extends BaseUseCase<List<TvShow>> implements Interactor<List<TvShow>, Integer> {

    private final TvShowRepository tvShowRepository;
    private final ThreadExecutor threadExecutor;
    private TvShowRepository.GetTvShowsCallback callback;
    private int page;

    private TvShowRepository.GetTvShowsCallback dataCallback = new TvShowRepository.GetTvShowsCallback() {

        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(List<TvShow> returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    @Inject
    public GetTvShowListInteractor(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, TvShowRepository tvShowRepository) {
        super(postExecutionThread);
        this.threadExecutor = threadExecutor;
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public void run() {
        tvShowRepository.getTvShows(page, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<List<TvShow>>> void execute(R defaultCallback, Integer args) {
        this.callback = ((TvShowRepository.GetTvShowsCallback) defaultCallback);
        this.page = args;
        threadExecutor.execute(this);
    }
}
