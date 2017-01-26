package com.jag.movies;

import android.app.Application;

import com.jag.movies.dependencyinjector.application.ApplicationComponent;
import com.jag.movies.dependencyinjector.application.ApplicationModule;
import com.jag.movies.dependencyinjector.application.DaggerApplicationComponent;

/**
 * Created by inlab on 26/01/2017.
 */

public class App extends Application {

    ApplicationComponent component = null;

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }
}
