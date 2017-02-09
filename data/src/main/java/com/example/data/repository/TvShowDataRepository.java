package com.example.data.repository;

import com.example.data.expception.DataErrorBundle;
import com.example.data.repository.datasource.tvshows.CacheTvShowDataSource;
import com.example.data.repository.datasource.tvshows.ReadableTvShowDataSource;
import com.example.entities.TvShow;
import com.example.repositories.TvShowRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Albert.Ruiz on 09/02/2017.
 */

public class TvShowDataRepository implements TvShowRepository {
    public static final String TAG = "TvShowDataRepository";
    private final ReadableTvShowDataSource dataSource;
    private final CacheTvShowDataSource cacheTvShowDataSource;
    public final static int RESULTS_FOR_PAGE = 20;

    //Tiempo entre cada actualizacion de la bd mediante red, en milisegundos.
    private final static long TIME_BETWEEN_NETWORK_UPDATE = 3 * 60 * 60 * 1000;

    @Inject
    public TvShowDataRepository(ReadableTvShowDataSource dataSource, CacheTvShowDataSource cacheTvShowDataSource) {
        this.dataSource = dataSource;
        this.cacheTvShowDataSource = cacheTvShowDataSource;
    }

    @Override
    public void getTvShows(int page, GetTvShowsCallback callback) {
        try {
            List<TvShow> tvShows;

            if (checkIfDoNetworkUpdate(page)) {
                updateFromNetwork(page, callback);
            } else {
                tvShows = cacheTvShowDataSource.getTvShowsByPage(page);

                if (tvShows.isEmpty() | tvShows.size() < RESULTS_FOR_PAGE) {
                    updateFromNetwork(page, callback);
                } else {
                    callback.onSuccess(tvShows);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(new DataErrorBundle(e));
        }
    }

    private void updateFromNetwork(int page, GetTvShowsCallback callback) throws IOException {
        List<TvShow> tvShows;
        tvShows = dataSource.getTvShowsByPage(page);
        callback.onSuccess(tvShows);
        cacheTvShowDataSource.saveTvShows(tvShows);
        cacheTvShowDataSource.setTimeFromLastUpdateCheck(page);
    }

    private boolean checkIfDoNetworkUpdate(int page) {
        Long timestampLastCheck = cacheTvShowDataSource.getTimeFromLastUpdateCheck(page);
        if (System.currentTimeMillis() - TIME_BETWEEN_NETWORK_UPDATE > timestampLastCheck) {
//            Log.e(TAG, "Page: " + String.valueOf(page) + " need network update");
            return true;
        }
//        Log.e(TAG, "Page: " + String.valueOf(page) + " no network update");
        return false;
    }


    @Override
    public void getTvShowById(int tvShowId, GetTvShowByIdCallback callback) {
        try {
            //Get tvShows form Realm
            TvShow tvShow = cacheTvShowDataSource.getTvShowById(tvShowId);

            if (tvShow == null) {
                // If data is not save at Realm DB, it is downloaded with retrofit and save.
                tvShow = dataSource.getTvShowById(tvShowId);
                callback.onSuccess(tvShow);
            } else {
                callback.onSuccess(tvShow);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onError(new DataErrorBundle(e));
        }
    }

    @Override
    public void updateTvShowFavorited(int tvShowId) {
        cacheTvShowDataSource.updateTvShowFavorited(tvShowId);
    }
}
