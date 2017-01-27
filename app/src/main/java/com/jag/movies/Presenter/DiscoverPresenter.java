package com.jag.movies.Presenter;

import com.jag.movies.Model.DiscoverModel;
import com.jag.movies.UI.DiscoverView;
import com.jag.movies.UI.IDiscoverView;

import javax.inject.Inject;


public class DiscoverPresenter {

    private final IDiscoverView discoverView;
    private final DiscoverModel discoverModel;

    @Inject
    public DiscoverPresenter(IDiscoverView discoverView, DiscoverModel discoverModel) {
        this.discoverView = discoverView;
        this.discoverModel = discoverModel;
    }
}
