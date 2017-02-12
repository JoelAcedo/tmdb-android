package com.example.data.mapper;

import com.example.data.realm.entities.TvShowRealm;
import com.example.data.realm.util.RealmString;
import com.example.data.retrofit.entities.TvShowDTO;
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

    public static TvShow fromTvShowDTO(TvShowDTO TvShowDTO) {
        return new TvShow(TvShowDTO.getOverview(),TvShowDTO.getName(),BASE_IMAGE_URL+TvShowDTO.getPosterPath(),
                TvShowDTO.getId(),TvShowDTO.getVoteAverage(),TvShowDTO.getPopularity(),
                TvShowDTO.getTvShowGenres(),false, TvShowDTO.getNumberOfSeasons(), TvShowDTO.getNumberOfEpisodes());
    }


    public static TvShow fromTvShowRealm(TvShowRealm TvShowRealm) {
        List<String> genres = new ArrayList<>();
        for (RealmString realmString : TvShowRealm.getGenreIds()) {
            genres.add(realmString.getString());
        }

        TvShow TvShow = new TvShow(TvShowRealm.getOverview(),TvShowRealm.getName(),TvShowRealm.getPosterPath(),
                TvShowRealm.getId(),TvShowRealm.getVoteAverage(),TvShowRealm.getPopularity(),
                genres,TvShowRealm.isFavorited(), TvShowRealm.getNumberOfSeasons(), TvShowRealm.getNumberOfEpisodes());

        return TvShow;
    }
}
