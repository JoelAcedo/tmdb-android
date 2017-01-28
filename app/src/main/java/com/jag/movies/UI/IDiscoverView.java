package com.jag.movies.UI;


import java.util.ArrayList;

public interface IDiscoverView {

    void showMovies(ArrayList<MovieViewModel> movieData);
    void startDetailActivity(int movieId);
}
