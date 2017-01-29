package com.jag.movies.dependencyinjector.application;

import com.jag.movies.UI.IDiscoverView;

import dagger.Module;
import dagger.Provides;

@Module
public class DiscoverModule {

    private IDiscoverView view;

    public DiscoverModule(IDiscoverView view) {
        this.view = view;
    }

    @Provides IDiscoverView providesDiscoverView() {
        return view;
    }
}
