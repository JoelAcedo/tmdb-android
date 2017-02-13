package com.example.data.mapper;

import com.example.data.realm.entities.TvShowRealm;
import com.example.data.realm.util.RealmSeason;
import com.example.data.realm.util.RealmString;
import com.example.data.retrofit.entities.SeasonDTO;
import com.example.data.retrofit.entities.TvShowDTO;
import com.example.entities.Season;
import com.example.entities.TvShow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class TvShowMapper {
    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w600";

    public static List<TvShow> fromTvShowListDTO(List<TvShowDTO> TvShowDTOList) {
        List<TvShow> TvShows = new ArrayList<>();
        for (TvShowDTO TvShowDTO : TvShowDTOList) {
            TvShow TvShow = fromTvShowDTO(TvShowDTO);
            TvShows.add(TvShow);
        }
        return TvShows;
    }

    public static TvShow fromTvShowDTO(TvShowDTO tvShowDTO) {
        List<Season> seasons = new ArrayList<>();

        if (tvShowDTO.getSeasonDTO() != null) {
            for (SeasonDTO s : tvShowDTO.getSeasonDTO()) {
                Season season = new Season(s.getEpisodeCount(), s.getSeasonNumber(), s.getPosterPath());
                seasons.add(season);
            }
        }

        return new TvShow(tvShowDTO.getOverview(),tvShowDTO.getName(),BASE_IMAGE_URL+tvShowDTO.getPosterPath(),
                tvShowDTO.getId(),tvShowDTO.getVoteAverage(),tvShowDTO.getPopularity(),
                tvShowDTO.getTvShowGenres(),false, tvShowDTO.getNumberOfSeasons(), tvShowDTO.getNumberOfEpisodes(), seasons,
                tvShowDTO.getReleaseDate());
    }


    public static TvShow fromTvShowRealm(TvShowRealm tvShowRealm) {
        List<String> genres = new ArrayList<>();
        for (RealmString realmString : tvShowRealm.getGenreIds()) {
            genres.add(realmString.getString());
        }

        List<Season> seasons= new ArrayList<>();
        for (RealmSeason s : tvShowRealm.getSeasons()) {
            Season season = new Season(s.getEpisodeCount(),s.getSeasonNumber(),s.getPosterPath());
            seasons.add(season);
        }

        TvShow tvShow = new TvShow(tvShowRealm.getOverview(),tvShowRealm.getName(),tvShowRealm.getPosterPath(),
                tvShowRealm.getId(),tvShowRealm.getVoteAverage(),tvShowRealm.getPopularity(),
                genres,tvShowRealm.isFavorited(), tvShowRealm.getNumberOfSeasons(), tvShowRealm.getNumberOfEpisodes(),
                seasons, tvShowRealm.getReleaseDate());

        return tvShow;
    }
}
