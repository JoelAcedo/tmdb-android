package com.jag.movies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert Ruiz on 27/01/2017.
 */

public class MovieDTO {


    @SerializedName("id")
    int id;

    @SerializedName("title")
    String title;

    @SerializedName("genre_ids")
    int[] genreIds = new int[0];

    @SerializedName("overview")
    String overview;

    @SerializedName("vote_average")
    float voteAverage;

    @SerializedName("release_date")
    String releaseDate;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public List<String> getMovieGenres() {
            List<String> genres = new ArrayList<>();
            int[] ids = getGenreIds();
            for (int i = 0; i < ids.length; i++) {
                MovieGenre genre = MovieGenre.getById(ids[i]);
                if (genre != null) {
                    genres.add(genre.getTitle());
                }
            }
            return genres;
    }


    public String getOverview() {
        return overview;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }




}
