package com.jag.movies.dependencyinjector.application;

import android.content.Context;

import com.jag.movies.App;
import com.jag.movies.Utils.PicassoLoader;
import com.jag.movies.dependencyinjector.qualifier.ForApp;
import com.jag.movies.Utils.GlideLoader;
import com.jag.movies.Utils.ImageLoader;

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

    @Provides
    @Singleton
    ImageLoader providesImageLoader(GlideLoader imageLoader) {
        return imageLoader;
    }
}
