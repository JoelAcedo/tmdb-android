package com.jag.movies.Model.DataSource;

import com.jag.movies.UI.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel Acedo on 27/01/2017.
 */

public class MovieFakeDataSource implements IMovieDataSource {

    public ArrayList<MovieViewModel> getData() {
        ArrayList<MovieViewModel> movies = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            MovieViewModel movieViewModel = getMovieDataById(1);

            movies.add(movieViewModel);
        }

        return movies;
    }

    public MovieViewModel getMovieDataById(int movieId) {
        List<String> genres = new ArrayList<String>();
        genres.add("Action");
        genres.add("Crime");
        genres.add("Fantasy");


        MovieViewModel movieViewModel = new MovieViewModel(movieId, "Suicide Squad",
                "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                5.9f,
                "2016-12-23",
                genres,
                "http://image.tmdb.org/t/p/w600/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg");

        return movieViewModel;
    }

}
