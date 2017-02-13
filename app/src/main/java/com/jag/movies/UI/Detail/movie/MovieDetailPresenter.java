package com.jag.movies.UI.Detail.movie;

import android.content.Intent;
import android.util.Log;

import com.example.entities.Actor;
import com.example.entities.Movie;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetMovieByIdInteractor;
import com.example.interactor.GetMovieCastInteractor;
import com.example.interactor.UpdateMovieFavoritedInteractor;
import com.example.repositories.CastRepository;
import com.example.repositories.MovieRepository;
import com.jag.movies.Mapper.CastMapper;
import com.jag.movies.Mapper.MovieMapper;
import com.jag.movies.Models.ActorViewModel;
import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MovieDetailPresenter {

    private static final String TAG = "MovieDetailPresenter";
    private final MovieDetailView movieDetailView;
    private final GetMovieByIdInteractor getMovieByIdInteractor;
    private final GetMovieCastInteractor getMovieCastInteractor;
    private final UpdateMovieFavoritedInteractor updateMovieFavoritedInteractor;
    private MovieViewModel movieViewModel;
    private ArrayList<ActorViewModel> cast;
    private int movieId;

    @Inject
    public MovieDetailPresenter(MovieDetailView movieDetailView, GetMovieByIdInteractor getMovieByIdInteractor,
                                GetMovieCastInteractor getMovieCastInteractor,
                                UpdateMovieFavoritedInteractor updateMovieFavoritedInteractor) {
        this.movieDetailView = movieDetailView;
        this.getMovieByIdInteractor = getMovieByIdInteractor;
        this.getMovieCastInteractor = getMovieCastInteractor;
        this.updateMovieFavoritedInteractor = updateMovieFavoritedInteractor;
    }

    public void onStart(Intent intent) {
        getExtrasFromIntent(intent);
        getMovieDataByID();
        getCastByMovieID();
    }

    private void movieDataReady() {
        movieDetailView.renderCover(movieViewModel.getCoverUrl());
        //movieDetailView.computePalette();
        movieDetailView.renderTitle(movieViewModel.getTitle());
        movieDetailView.renderOverview(movieViewModel.getOverview());
        movieDetailView.renderGenres(movieViewModel.getGenresList());
        movieDetailView.renderScore(movieViewModel.getVoteAverage());
        movieDetailView.renderReleaseDate(movieViewModel.getReleaseDate());

//        Log.e(TAG, String.valueOf(movieViewModel.isFavorited()));

        if (movieViewModel.isFavorited()) {
            movieDetailView.setFloatingButtonFavorited();
        } else {
            movieDetailView.setFloatingButtonNotFavorited();
        }
    }

    public void floatingButtonClicked() {
        if (movieViewModel.isFavorited()) {
            movieViewModel.setFavorite(false);
            movieDetailView.setFloatingButtonNotFavorited();
        } else {
            movieViewModel.setFavorite(true);
            movieDetailView.setFloatingButtonFavorited();
        }
        updateMovieFavoritedInteractor.execute(null, movieId);
    }

    private void getMovieDataByID() {
        getMovieByIdInteractor.execute(new MovieRepository.GetMovieByIdCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(Movie returnParam) {
                movieViewModel = MovieMapper.toMovieViewModel(returnParam);
                movieDataReady();
            }
        }, movieId);
    }

    private void getExtrasFromIntent(Intent intent) {
        if (intent != null) {
            movieId = intent.getIntExtra(MovieDetailActivity.ID_MOVIE, 0);
        }
        else {
            movieId = 0;
        }
    }


    public void updateVibrantColor(int vibrantColor) {
        movieDetailView.renderColors(vibrantColor);
    }

    private void getCastByMovieID() {
        getMovieCastInteractor.execute(new CastRepository.GetCastCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<Actor> returnParam) {
                movieDetailView.showCast(CastMapper.toListActorViewModel(returnParam));
            }
        }, movieId);
    }
}
