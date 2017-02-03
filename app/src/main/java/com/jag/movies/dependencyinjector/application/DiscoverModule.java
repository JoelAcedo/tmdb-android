package com.jag.movies.dependencyinjector.application;

import com.jag.movies.UI.Discover.DiscoverView;

import dagger.Module;
import dagger.Provides;

@Module
public class DiscoverModule {

    private DiscoverView view;

    public DiscoverModule(DiscoverView view) {
        this.view = view;
    }

    @Provides
    DiscoverView providesDiscoverView() {
        return view;
    }
}
