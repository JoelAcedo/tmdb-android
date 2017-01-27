package com.jag.movies.dependencyinjector.application;

import com.jag.movies.App;
import com.jag.movies.dependencyinjector.activity.ActivityComponent;
import com.jag.movies.dependencyinjector.activity.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App application);

    ActivityComponent plus(ActivityModule activityModule, DiscoverModule discoverModule);
}
