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

public class GetMovieListInteractor extends BaseUseCase<List<Movie>> implements Interactor<List<Movie>, Void> {

    private final MovieRepository movieRepository;
    private final ThreadExecutor threadExecutor;
    private MovieRepository.GetMoviesCallback callback;

    private MovieRepository.GetMoviesCallback dataCallback = new MovieRepository.GetMoviesCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(List<Movie> returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    @Inject
    public GetMovieListInteractor(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, MovieRepository movieRepository) {
        super(postExecutionThread);
        this.movieRepository = movieRepository;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public void run() {
        movieRepository.getMovies(dataCallback);
    }

    @Override
    public <R extends DefaultCallback<List<Movie>>> void execute(R defaultCallback, Void args) {
        this.callback = ((MovieRepository.GetMoviesCallback) defaultCallback);
        threadExecutor.execute(this);
    }
}
