package com.example.data.mapper;

import com.example.data.entities.MovieDTO;
import com.example.entities.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inlab on 01/02/2017.
 */

public class MovieMapper {

    public static List<Movie> fromMovieListDTO(List<MovieDTO> movieDTOList) {
        List<Movie> movies = new ArrayList<>();
        for (MovieDTO movieDTO : movieDTOList) {
            Movie movie = fromMovieDTO(movieDTO);
            movies.add(movie);
        }
        return movies;
    }

    public static Movie fromMovieDTO(MovieDTO movieDTO) {
        return new Movie(movieDTO.getId(), movieDTO.getTitle(), movieDTO.getOverview(), movieDTO.getVoteAverage(),
                movieDTO.getReleaseDate(), movieDTO.getMovieGenres(), movieDTO.getPosterPath());
    }


}
