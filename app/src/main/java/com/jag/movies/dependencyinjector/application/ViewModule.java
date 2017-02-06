package com.jag.movies.dependencyinjector.application;

import com.jag.movies.UI.Detail.DetailView;
import com.jag.movies.UI.Discover.DiscoverView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private DiscoverView discoverView;
    private DetailView detailView;

    public ViewModule(DiscoverView discoverView) {
        this.discoverView = discoverView;
    }


    public ViewModule(DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides
    DiscoverView providesDiscoverView() {
        return discoverView;
    }

    @Provides
    DetailView providesDetailView() {
        return detailView;
    }
}
