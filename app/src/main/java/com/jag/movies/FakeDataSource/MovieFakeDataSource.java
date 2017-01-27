package com.jag.movies.FakeDataSource;

import com.jag.movies.UI.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel Acedo on 27/01/2017.
 */

public class MovieFakeDataSource {

    public ArrayList<MovieViewModel> getFakeData() {
        ArrayList<MovieViewModel> movies = new ArrayList<>();

        List<String> genres = new ArrayList<String>();
        genres.add("Action");
        genres.add("Crime");
        genres.add("Fantasy");

        for (int i = 0; i < 5; i++) {
            MovieViewModel movieViewModel = new MovieViewModel(1, "Suicide Squad",
                    "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted",
                    5.9f,
                    "2016-12-23",
                    genres,
                    "http://image.tmdb.org/t/p/w600/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg");

            movies.add(movieViewModel);
        }

        return movies;
    }

}
