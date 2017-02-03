package com.example.interactor;

import com.example.entities.Movie;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.MovieRepository;

import javax.inject.Inject;

/**
 * Created by inlab on 03/02/2017.
 */

public class UpdateMovieFavoritedInteractor extends BaseUseCase<Movie> implements Interactor<Void, Integer>{

    //MovieRepository.UpdateMovieFavoritedCallback callback;
    private Integer movieId;


    private final MovieRepository movieRepository;
    private final ThreadExecutor executor;

    @Inject
    public UpdateMovieFavoritedInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, MovieRepository movieRepository) {
        super(postExecutionThread);
        this.movieRepository = movieRepository;
        this.executor = executor;
    }

    @Override
    public void run() {
        movieRepository.updateMovieFavorited(movieId);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(R defaultCallback, Integer args) {
        this.movieId = args;
        executor.execute(this);
    }
}
