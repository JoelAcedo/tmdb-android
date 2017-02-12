package com.example.data.repository.datasource.tvshows;

import com.example.data.mapper.TvShowMapper;
import com.example.data.realm.entities.TvShowRealm;
import com.example.data.realm.util.LastUpdateTimeByPageRealm;
import com.example.data.realm.util.RealmString;
import com.example.data.repository.TvShowDataRepository;
import com.example.entities.TvShow;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.internal.IOException;

import static com.example.data.realm.util.LastUpdateTimeByPageRealm.PAGE_ID_REALM;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class RealmTvShowDataSource implements CacheTvShowDataSource {

        @Inject
        public RealmTvShowDataSource() {
        }

        @Override
        public List<TvShow> getTvShowsByPage(int page) throws IOException {
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            RealmResults<TvShowRealm> tvShowsRealm = realm.where(TvShowRealm.class)
                    .findAllSorted(TvShowRealm.TVSHOW_POPULARITY_REALM, Sort.DESCENDING);

            List<TvShow> tvShows = new ArrayList<>();
            int maxSize = Math.min(tvShowsRealm.size(), TvShowDataRepository.RESULTS_FOR_PAGE * page);
            for (int i = (TvShowDataRepository.RESULTS_FOR_PAGE * (page-1)); i < maxSize; i++) {
                TvShowRealm tvShowRealm = tvShowsRealm.get(i);
                tvShows.add(TvShowMapper.fromTvShowRealm(tvShowRealm));
            }

            realm.commitTransaction();
            return tvShows;
        }

        @Override
        public TvShow getTvShowById(int tvShowId) throws IOException {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            TvShowRealm tvShowRealm = realm.where(TvShowRealm.class).equalTo(TvShowRealm.TVSHOW_ID_REALM, tvShowId)
                    .findFirst();

            if (tvShowRealm == null) return null;

            TvShow tvShow = TvShowMapper.fromTvShowRealm(tvShowRealm);
            realm.commitTransaction();
            return tvShow;
        }

        @Override
        public void saveTvShows(List<TvShow> tvShows) {
//            Realm realm = Realm.getDefaultInstance();
//
//            realm.beginTransaction();
//            for (TvShow tvShow : tvShows) {
//                RealmList<RealmString> realmGenres = new RealmList<>();
//                for (String genre : tvShow.getGenresList()) {
//                    realmGenres.add(new RealmString(genre));
//                }
//
//                TvShowRealm tvShowRealm = new TvShowRealm(tvShow.getPoster_path(), tvShow.getOverview(),
//                        realmGenres, tvShow.getId(), tvShow.getVoteAverage(), tvShow.getName(),
//                        tvShow.isFavorited(), tvShow.getPopularity(), tvShow.getNumberOfEspisodes(),
//                        tvShow.getNumberOfSeasons());
//                realm.copyToRealmOrUpdate(tvShowRealm);
//            }
//            realm.commitTransaction();
        }

        @Override
        public void updateTvShowFavorited(int tvShowId) {
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            TvShowRealm tvShowRealm = realm.where(TvShowRealm.class).equalTo(TvShowRealm.TVSHOW_ID_REALM, tvShowId)
                    .findFirst();
            tvShowRealm.setFavorited(!tvShowRealm.isFavorited());
            realm.copyToRealm(tvShowRealm);
            realm.commitTransaction();
        }


        @Override
        public long getTimeFromLastUpdateCheck(int page) {
            Realm realm = Realm.getDefaultInstance();

            Long timestamp;
            realm.beginTransaction();
            LastUpdateTimeByPageRealm lastUpdateTimeByPageRealm = realm.where(LastUpdateTimeByPageRealm.class).equalTo(PAGE_ID_REALM, page).findFirst();
            if (lastUpdateTimeByPageRealm == null) {
                timestamp = 0l;
            }
            else {
                timestamp = lastUpdateTimeByPageRealm.getTimestampInMilis();
            }
            realm.commitTransaction();

            return timestamp;
        }

        @Override
        public void setTimeFromLastUpdateCheck(int page) {
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            LastUpdateTimeByPageRealm lastUpdateTimeByPageRealm = new LastUpdateTimeByPageRealm(System.currentTimeMillis(), page);
            realm.copyToRealmOrUpdate(lastUpdateTimeByPageRealm);
            realm.commitTransaction();
        }

    }
