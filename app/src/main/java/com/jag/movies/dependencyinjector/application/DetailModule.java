package com.jag.movies.dependencyinjector.application;

import com.jag.movies.UI.IDetailView;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {
    private IDetailView detailView;

    public DetailModule(IDetailView detailView) {
        this.detailView = detailView;
    }

    @Provides IDetailView providesDetailView() {
        return detailView;
    }
}
