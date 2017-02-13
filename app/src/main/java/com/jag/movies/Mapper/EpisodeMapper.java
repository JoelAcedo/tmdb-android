package com.jag.movies.Mapper;

import com.example.entities.Episode;
import com.jag.movies.Models.EpisodeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joela on 13/02/2017.
 */

public class EpisodeMapper {
    public static List<EpisodeViewModel> toListEpisodeViewModel(List<Episode> episodeList) {
        List<EpisodeViewModel> episodes = new ArrayList<>();
        for (Episode episode : episodeList) {
            episodes.add(toEpisodeViewModel(episode));
        }

        return episodes;
    }

    private static EpisodeViewModel toEpisodeViewModel(Episode episode) {
        return new EpisodeViewModel(episode.getId(),episode.getEpisodeNumber(), episode.getName(),
                episode.getReleaseDate(), episode.getOverview(), episode.getPosterPath());
    }
}
