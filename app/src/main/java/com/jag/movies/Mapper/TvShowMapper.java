package com.jag.movies.Mapper;

import com.example.entities.Season;
import com.example.entities.TvShow;
import com.jag.movies.Models.SeasonViewModel;
import com.jag.movies.Models.TvShowViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class TvShowMapper {
    public static List<TvShowViewModel> toListTvShowViewModel(List<TvShow> tvShowList) {
        List<TvShowViewModel> tvShows = new ArrayList<>();
        for (TvShow tvShow : tvShowList) {
            TvShowViewModel tvShowViewModel = toTvShowViewModel(tvShow);
            tvShows.add(tvShowViewModel);
        }
        return tvShows;
    }

    public static TvShowViewModel toTvShowViewModel(TvShow tvShow) {
        List<SeasonViewModel> seasons= new ArrayList<>();
        for (Season s : tvShow.getSeasons()) {
            SeasonViewModel season = new SeasonViewModel(s.getEpisodeCount(),s.getSeasonNumber(),s.getPosterPath());
            seasons.add(season);
        }

        return new TvShowViewModel(tvShow.getId(),tvShow.getName(),tvShow.getOverview(),tvShow.getVoteAverage(),
                tvShow.getGenresList(),tvShow.getCoverUrl(),tvShow.isFavorited(), tvShow.getNumberOfSeasons(), tvShow.getNumberOfEspisodes(), seasons);
    }
}
