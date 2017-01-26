package com.jag.movies.main.Presenter;

import com.jag.movies.main.Model.DiscoverModel;
import com.jag.movies.main.UI.IDiscoverView;


public class DiscoverPresenter {

    private final IDiscoverView discoverView;
    private final DiscoverModel discoverModel;

    public DiscoverPresenter(IDiscoverView discoverView, DiscoverModel discoverModel) {
        this.discoverView = discoverView;
        this.discoverModel = discoverModel;
    }
}
