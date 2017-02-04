package com.jag.movies.UI.Discover;


import android.widget.ImageView;

import com.jag.movies.Models.MovieViewModel;

import java.util.List;

public interface DiscoverView {

    void showMovies(List<MovieViewModel> movieViewModelData);
    void addMovies(List<MovieViewModel> movieViewModelData);

    void startDetailActivity(int movieId, ImageView movieCover);
    void updateMovieFavoritedState(MovieViewModel movie, int position);
}
