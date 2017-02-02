package com.example.interactor;

import com.example.entities.Movie;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by inlab on 01/02/2017.
 */

public class GetMovieByIdInteractor extends BaseUseCase<Movie> implements Interactor<Movie, Integer>{

    MovieRepository.GetMovieByIdCallback callback;
    private Integer movieId;

    MovieRepository.GetMovieByIdCallback dataCallback = new MovieRepository.GetMovieByIdCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Movie returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final MovieRepository movieRepository;
    private final ThreadExecutor executor;

    @Inject
    public GetMovieByIdInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, MovieRepository movieRepository) {
        super(postExecutionThread);
        this.movieRepository = movieRepository;
        this.executor = executor;
    }

    @Override
    public void run() {
        movieRepository.getMovieById(movieId, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Movie>> void execute(R callback, Integer args) {
        this.callback = ((MovieRepository.GetMovieByIdCallback) callback);
        this.movieId = args;
        executor.execute(this);
    }


}
