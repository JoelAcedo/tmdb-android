package com.jag.movies.dependencyinjector.activity;

import android.app.Activity;
import android.content.Context;

import com.jag.movies.App;
import com.jag.movies.UI.DiscoverView;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(DiscoverView activity){
        this.activity = activity;
    }


    @Provides
    @PerActivity
    @ForActivity
    Context providesContext(){
        return activity;
    }

}
