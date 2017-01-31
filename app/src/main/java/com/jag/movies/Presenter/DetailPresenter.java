package com.jag.movies.Presenter;

import android.content.Intent;

import com.jag.movies.Callbacks.ActorListCallback;
import com.jag.movies.Model.DetailModel;
import com.jag.movies.Callbacks.MovieCallback;
import com.jag.movies.Retrofit.ActorDTO;
import com.jag.movies.Retrofit.MovieDTO;
import com.jag.movies.UI.ActorViewModel;
import com.jag.movies.UI.DetailActivity;
import com.jag.movies.UI.IDetailView;
import com.jag.movies.UI.MovieViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import java.util.ArrayList;

import javax.inject.Inject;

@PerActivity
public class DetailPresenter {

    private final IDetailView detailView;
    private final DetailModel detailModel;
    private MovieViewModel movie;
    private ArrayList<ActorViewModel> cast;
    private int movieId;

    @Inject
    public DetailPresenter(IDetailView detailView, DetailModel detailModel) {
        this.detailView = detailView;
        this.detailModel = detailModel;
    }

    public void onStart(Intent intent) {
        getExtrasFromIntent(intent);
        getMovieDataByID();
        getCastByMovieID();
    }

    private void movieDataReady() {
        detailView.renderCover(movie.getCoverUrl());
        //detailView.computePalette();
        detailView.renderTitle(movie.getTitle());
        detailView.renderOverview(movie.getOverview());
        detailView.renderGenres(movie.getGenresList());
        detailView.renderScore(movie.getVoteAverage());
        detailView.renderReleaseDate(movie.getReleaseDate());
    }

    public void floatingButtonClicked() {
        if (movie.isFavorited()) {
            movie.setFavorite(false);
            detailView.setFloatingButtonNotFavorited();
        } else {
            movie.setFavorite(true);
            detailView.setFloatingButtonFavorited();
        }
    }

    private void getMovieDataByID() {
        detailModel.getMovieByIndex(movieId, new MovieCallback() {
            @Override
            public void movieMapper(MovieDTO movieDTO) {
                movie = new MovieViewModel(movieDTO.getId(), movieDTO.getTitle(),
                        movieDTO.getOverview(), movieDTO.getVoteAverage(), movieDTO.getReleaseDate(),
                        movieDTO.getMovieGenres(), "http://image.tmdb.org/t/p/w600" + movieDTO.getPosterPath());
                movieDataReady();
            }
        });
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
        detailView.renderToolbarColors(vibrantColor);
    }

    private void getCastByMovieID() {
        detailModel.getCastByMovieId(movieId, new ActorListCallback() {
            @Override
            public void dataReady(ArrayList<ActorViewModel> cast) {
                //TODO: detailView.showCast(cast); /*to show the actors list in the recyclerView*/
            }
        });
    }
}
