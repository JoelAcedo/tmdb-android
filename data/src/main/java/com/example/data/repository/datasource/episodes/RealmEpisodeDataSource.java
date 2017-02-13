package com.example.data.repository.datasource.episodes;

import com.example.data.mapper.EpisodeMapper;
import com.example.data.realm.entities.EpisodeRealm;
import com.example.entities.Episode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by joela on 13/02/2017.
 */

public class RealmEpisodeDataSource implements CacheEpisodeDataSource {

    @Inject
    public RealmEpisodeDataSource() {
    }

    @Override
    public List<Episode> getEpisodes(int tvShowId, int season) throws IOException {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<EpisodeRealm> episodesRealms = realm.where(EpisodeRealm.class)
                .equalTo(EpisodeRealm.EPISODE_TVSHOW_ID, tvShowId)
                .equalTo(EpisodeRealm.EPISODE_SEASON_NUMBER, season).findAll();

        List<Episode> episodes = new ArrayList<>();
        for (EpisodeRealm episodeRealm : episodesRealms) {
            episodes.add(EpisodeMapper.fromEpisodeRealm(episodeRealm));
        }

        return episodes;
    }

    @Override
    public void saveEpisodes(List<Episode> episodes) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for (Episode episode : episodes) {
            EpisodeRealm episodeRealm = new EpisodeRealm(episode.getId(), episode.getEpisodeNumber(),
                    episode.getName(), episode.getReleaseDate(), episode.getOverview(),
                    episode.getPosterPath(), episode.getTvShowId(), episode.getSeasonNumber());
            realm.copyToRealm(episodeRealm);
        }
        realm.commitTransaction();
    }

}
