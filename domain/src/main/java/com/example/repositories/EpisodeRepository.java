package com.example.repositories;

import com.example.entities.Episode;
import com.example.interactor.DefaultCallback;

import java.util.List;

/**
 * Created by joela on 13/02/2017.
 */

public interface EpisodeRepository {

    interface GetEpisodesCallback extends DefaultCallback<List<Episode>> {}

    void getEpisodes(int tvShowId, int seasonNumber, GetEpisodesCallback callback);

}
