package com.jag.movies.dependencyinjection.activity;

/**
 * Created by Albert.Ruiz on 26/01/2017.
 */

import android.app.Activity;
import android.content.Context;

import com.jag.movies.dependencyinjection.qualifier.ForActivity;
import com.jag.movies.dependencyinjection.scope.PerActivity;
import com.jag.movies.main.UI.DiscoverView;

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
