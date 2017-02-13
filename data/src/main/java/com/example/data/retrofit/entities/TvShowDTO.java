package com.example.data.retrofit.entities;

import com.example.entities.Season;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class TvShowDTO {
    @SerializedName("overview")
    public String overview;
    @SerializedName("name")
    public String name;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("id")
    public int id;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("genres")
    private List<Genre> genresList;
    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private int numberOfSeasons;
    @SerializedName("seasons")
    private List<SeasonDTO> seasonDTO;
    @SerializedName("first_air_date")
    private String releaseDate;

    public TvShowDTO(String overview, String name, String posterPath, int id, float voteAverage, float popularity, List<Genre> genresList, int numberOfEpisodes,
                     int numberOfSeasons, String releaseDate, List<SeasonDTO> seasons) {
        this.overview = overview;
        this.name = name;
        this.posterPath = posterPath;
        this.id = id;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.genresList = genresList;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.releaseDate = releaseDate;
        this.seasonDTO = seasons;
    }

    public List<SeasonDTO> getSeasonDTO() {
        return seasonDTO;
    }

    public int getNumberOfEpisodesPerSeason(int seasonNumber) {
        for (SeasonDTO s: seasonDTO) {
            if (seasonNumber == s.getSeasonNumber()) {
                return s.getEpisodeCount();
            }
        }
        return 0;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public List<String> getTvShowGenres() {
        List<String> genres = new ArrayList<>();

        List<Integer> ids = new ArrayList<>();
        if (genreIds == null) {
            for (Genre genre : getGenresList()) {
                ids.add(genre.getId());
            }
        } else {
            ids = genreIds;
        }

        for (int i = 0; i < ids.size(); i++) {
            MovieGenre genre = MovieGenre.getById(ids.get(i));
            if (genre != null) {
                genres.add(genre.getTitle());
            }
        }
        return genres;
    }

    public float getPopularity() {
        return popularity;
    }

    public List<Genre> getGenresList() {
        return genresList;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
