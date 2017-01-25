package com.jag.movies.Presenter;

import com.jag.movies.Model.DiscoverModel;
import com.jag.movies.Model.IDiscoverModel;
import com.jag.movies.UI.IDiscoverView;


public class DiscoverPresenter implements IDiscoverPresenter {

    private final IDiscoverView discoverView;
    private final IDiscoverModel discoverModel;

    public DiscoverPresenter(IDiscoverView discoverView, IDiscoverModel discoverModel) {
        this.discoverView = discoverView;
        this.discoverModel = discoverModel;
    }
}
