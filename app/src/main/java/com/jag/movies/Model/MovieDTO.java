package com.jag.movies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joela on 30/01/2017.
 */

public class MovieDTO {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("genres")
    private List<Genre> genresList;

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private float voteAverage;

    public MovieDTO(String posterPath, String overview, String releaseDate, List<Genre> genresList, Integer id,
                    String title, float voteAverage) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genresList = genresList;
        this.id = id;
        this.title = title;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Genre> getGenresList() {
        return genresList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public List<String> getMovieGenres() {
        List<String> genres = new ArrayList<>();

        List<Integer> ids = new ArrayList<>();
        for (Genre genre : getGenresList()) {
            ids.add(genre.getId());
        }

        for (int i = 0; i < ids.size(); i++) {
            MovieGenre genre = MovieGenre.getById(ids.get(i));
            if (genre != null) {
                genres.add(genre.getTitle());
            }
        }
        return genres;
    }


}
