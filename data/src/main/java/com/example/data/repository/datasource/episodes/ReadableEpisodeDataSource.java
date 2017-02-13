package com.example.data.repository.datasource.episodes;

import com.example.entities.Episode;

import java.io.IOException;
import java.util.List;

/**
 * Created by joela on 13/02/2017.
 */

public interface ReadableEpisodeDataSource {

    List<Episode> getEpisodes(int tvShowId, int season) throws IOException;
}
