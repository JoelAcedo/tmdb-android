package com.jag.movies.dependencyinjector.application;

import com.jag.movies.UI.Detail.DetailView;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {
    private DetailView detailView;

    public DetailModule(DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides
    DetailView providesDetailView() {
        return detailView;
    }
}
