package com.jag.movies.Presenter;

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
import com.jag.movies.UI.Models.ActorViewModel;
import com.jag.movies.UI.DetailActivity;
import com.jag.movies.UI.IDetailView;
import com.jag.movies.UI.Models.MovieViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class DetailPresenter {

    private static final String TAG = "DetailPresenter";
    private final IDetailView detailView;
    private final GetMovieByIdInteractor getMovieByIdInteractor;
    private final GetMovieCastInteractor getMovieCastInteractor;
    private final UpdateMovieFavoritedInteractor updateMovieFavoritedInteractor;
    private MovieViewModel movieViewModel;
    private ArrayList<ActorViewModel> cast;
    private int movieId;

    @Inject
    public DetailPresenter(IDetailView detailView, GetMovieByIdInteractor getMovieByIdInteractor,
                           GetMovieCastInteractor getMovieCastInteractor,
                           UpdateMovieFavoritedInteractor updateMovieFavoritedInteractor) {
        this.detailView = detailView;
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
        detailView.renderCover(movieViewModel.getCoverUrl());
        //detailView.computePalette();
        detailView.renderTitle(movieViewModel.getTitle());
        detailView.renderOverview(movieViewModel.getOverview());
        detailView.renderGenres(movieViewModel.getGenresList());
        detailView.renderScore(movieViewModel.getVoteAverage());
        detailView.renderReleaseDate(movieViewModel.getReleaseDate());

        Log.e(TAG, String.valueOf(movieViewModel.isFavorited()));

        if (movieViewModel.isFavorited()) {
            detailView.setFloatingButtonFavorited();
        } else {
            detailView.setFloatingButtonNotFavorited();
        }
    }

    public void floatingButtonClicked() {
        if (movieViewModel.isFavorited()) {
            movieViewModel.setFavorite(false);
            detailView.setFloatingButtonNotFavorited();
        } else {
            movieViewModel.setFavorite(true);
            detailView.setFloatingButtonFavorited();
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
            movieId = intent.getIntExtra(DetailActivity.ID_MOVIE, 0);
        }
        else {
            movieId = 0;
        }
    }


    public void updateVibrantColor(int vibrantColor) {
        detailView.renderColors(vibrantColor);
    }

    private void getCastByMovieID() {
        getMovieCastInteractor.execute(new CastRepository.GetCastCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.e(TAG, errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<Actor> returnParam) {
                detailView.showCast(CastMapper.toListActorViewModel(returnParam));
            }
        }, movieId);
    }
}
