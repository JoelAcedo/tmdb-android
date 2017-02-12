package com.jag.movies.dependencyinjector.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @ForActivity
    Context providesContext() {
        return activity;
    }

    @Provides
    @PerActivity
    public FragmentManager providesFragmentManager() {
        return activity.getFragmentManager();
    }
}
