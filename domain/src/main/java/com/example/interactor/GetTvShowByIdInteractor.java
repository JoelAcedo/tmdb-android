package com.example.interactor;

import com.example.entities.TvShow;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.TvShowRepository;

import javax.inject.Inject;

/**
 * Created by joela on 13/02/2017.
 */

public class GetTvShowByIdInteractor extends BaseUseCase<TvShow> implements Interactor<TvShow, Integer> {

    TvShowRepository.GetTvShowByIdCallback callback;
    private Integer tvShowId;

    TvShowRepository.GetTvShowByIdCallback dataCallback = new TvShowRepository.GetTvShowByIdCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(TvShow returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };


    private final TvShowRepository tvShowRepository;
    private final ThreadExecutor executor;

    @Inject
    public GetTvShowByIdInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, TvShowRepository tvShowRepository) {
        super(postExecutionThread);
        this.executor = executor;
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public void run() {
        tvShowRepository.getTvShowById(tvShowId, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<TvShow>> void execute(R defaultCallback, Integer args) {
        this.callback = ((TvShowRepository.GetTvShowByIdCallback) defaultCallback);
        this.tvShowId = args;
        executor.execute(this);
    }
}
