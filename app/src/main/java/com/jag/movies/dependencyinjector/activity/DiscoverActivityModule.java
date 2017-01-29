package com.jag.movies.dependencyinjector.activity;

import android.app.Activity;
import android.content.Context;

import com.jag.movies.UI.DiscoverActivity;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class DiscoverActivityModule {

    private Activity activity;

    public DiscoverActivityModule(DiscoverActivity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @ForActivity
    Context providesContext(){
        return activity;
    }

}
