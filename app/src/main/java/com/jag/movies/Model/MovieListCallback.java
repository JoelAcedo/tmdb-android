package com.jag.movies.Model;

import com.jag.movies.UI.MovieViewModel;

import java.util.ArrayList;

/**
 * Created by joela on 29/01/2017.
 */
public interface MovieListCallback {

    void dataReady(ArrayList<MovieViewModel> movies);
}
