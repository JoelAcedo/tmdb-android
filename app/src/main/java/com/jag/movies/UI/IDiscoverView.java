package com.jag.movies.UI;


import android.widget.ImageView;

import com.jag.movies.UI.Models.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public interface IDiscoverView {

    void showMovies(List<MovieViewModel> movieViewModelData);
    void startDetailActivity(int movieId, ImageView movieCover);
}
