package com.example.data.repository.datasource.episodes;

import com.example.entities.Episode;

import java.util.List;

/**
 * Created by joela on 13/02/2017.
 */

public interface WriteableEpisodeDataSource {

    void saveEpisodes(List<Episode> episodes);

}
