package com.jag.movies.UI.Discover;

import android.util.Log;
import android.widget.ImageView;

import com.example.entities.Movie;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetMovieByIdInteractor;
import com.example.interactor.GetMovieListInteractor;
import com.example.repositories.MovieRepository;
import com.jag.movies.Mapper.MovieMapper;
import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class DiscoverPresenter {

    private static final String TAG = "DiscoverPresenter";
    private final DiscoverView discoverView;
    private final GetMovieListInteractor getMovieListInteractor;
    private final GetMovieByIdInteractor getMovieByIdInteractor;
    private int page;
    private int position;
    private int movieId;

    @Inject
    public DiscoverPresenter(DiscoverView discoverView, GetMovieListInteractor getMovieListInteractor, GetMovieByIdInteractor getMovieByIdInteractor) {
        this.discoverView = discoverView;
        this.getMovieListInteractor = getMovieListInteractor;
        this.getMovieByIdInteractor = getMovieByIdInteractor;
        this.page = 1;
        this.position = -1;
    }
}
