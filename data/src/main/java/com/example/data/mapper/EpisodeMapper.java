package com.example.data.mapper;

import com.example.data.realm.entities.EpisodeRealm;
import com.example.data.retrofit.entities.EpisodeDTO;
import com.example.entities.Episode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joela on 13/02/2017.
 */
public class EpisodeMapper {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w600";

    public static List<Episode> fromEpisodeListDTO(List<EpisodeDTO> episodesList, int tvShowId,
                                                   int seasonNumber) {
        List<Episode> episodes = new ArrayList<>();
        for (EpisodeDTO episodeDTO : episodesList) {
            String imagePath;
            if (episodeDTO.getPosterPath() != null) {
                imagePath = BASE_IMAGE_URL + episodeDTO.getPosterPath();
            } else {
                imagePath = null;
            }

            episodes.add(new Episode(episodeDTO.getId(), episodeDTO.getEpisodeNumber(), episodeDTO.getName(),
                    episodeDTO.getReleaseDate(), episodeDTO.getOverview(), imagePath, tvShowId,
                    seasonNumber));
        }

        return episodes;
    }

    public static Episode fromEpisodeRealm(EpisodeRealm episodeRealm) {
        return new Episode(episodeRealm.getId(), episodeRealm.getEpisodeNumber(),
                episodeRealm.getName(), episodeRealm.getReleaseDate(),
                episodeRealm.getOverview(), episodeRealm.getPosterPath(),
                episodeRealm.getTvShowId(), episodeRealm.getSeasonNumber());
    }
}
