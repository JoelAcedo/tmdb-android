package com.example.interactor;

import com.example.entities.Actor;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.CastRepository;

import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public class GetMovieCastInteractor extends BaseUseCase<List<Actor>> implements Interactor<List<Actor>, Integer> {
    private final CastRepository castRepository;
    private final ThreadExecutor threadExecutor;
    private CastRepository.GetCastCallback callback;
    private Integer moveId;

    private CastRepository.GetCastCallback dataCallback = new CastRepository.GetCastCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(List<Actor> returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    public GetMovieCastInteractor(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, CastRepository castRepository) {
        super(postExecutionThread);
        this.castRepository = castRepository;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public void run() {
        castRepository.getCastByMovieId(moveId,dataCallback);
    }

    @Override
    public <R extends DefaultCallback<List<Actor>>> void execute(R defaultCallback, Integer args) {
        this.callback = ((CastRepository.GetCastCallback) defaultCallback);
        this.moveId = args;
        threadExecutor.execute(this);
    }
}
