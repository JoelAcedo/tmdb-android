package com.jag.movies.Mapper;

import com.example.entities.Movie;
import com.jag.movies.UI.Models.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public class MovieMapper {

    public static List<MovieViewModel> toListMovieViewModel(List<Movie> movieList) {
        List<MovieViewModel> movies = new ArrayList<>();
        for (Movie movie : movieList) {
            MovieViewModel movieViewModel = toMovieViewModel(movie);
            movies.add(movieViewModel);
        }
        return movies;
    }

    public static MovieViewModel toMovieViewModel(Movie movie) {
        return new MovieViewModel(movie.getMovieId(), movie.getTitle(), movie.getOverview(), movie.getVoteAverage(),
                movie.getReleaseDate(), movie.getGenresList(), movie.getCoverUrl(), movie.isFavorited());
    }

}
