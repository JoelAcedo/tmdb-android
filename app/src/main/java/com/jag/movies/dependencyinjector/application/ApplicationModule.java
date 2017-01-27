package com.jag.movies.dependencyinjector.application;

import android.content.Context;

import com.jag.movies.App;
import com.jag.movies.dependencyinjector.qualifier.ForApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ForApp
    public Context providesContext() {
        return app;
    }
}
