package com.jag.movies.UI.Discover.fragments;

import android.widget.ImageView;

import javax.inject.Inject;

/**
 * Created by joela on 12/02/2017.
 */
public class TvShowsPresenter {

    private static final String TAG = "MoviePresenter";
    private final FragmentDiscoverView discoverView;
//    private final GetMovieListInteractor getMovieListInteractor;
//    private final GetMovieByIdInteractor getMovieByIdInteractor;
    private int page;
    private int position;
    private int tvShowId;

    @Inject
    public TvShowsPresenter(FragmentDiscoverView discoverView) {
        this.discoverView = discoverView;
        this.page = 1;
        this.position = -1;
    }

    public void tvShowClicked(int tvShowId, int position, ImageView cover) {
        discoverView.startDetailActivity(tvShowId, cover);
        this.tvShowId = tvShowId;
        this.position = position;
    }

    public void onResume() {

    }

    public void onCreate() {

    }

    public void onLoadMore(int page) {

    }
}
