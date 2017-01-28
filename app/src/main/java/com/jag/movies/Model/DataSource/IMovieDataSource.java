package com.jag.movies.Model.DataSource;

import com.jag.movies.UI.MovieViewModel;

import java.util.List;



public interface IMovieDataSource {

    //TODO change movieviewmodel por movieDTO or similar
    List<MovieViewModel> getData();
}
