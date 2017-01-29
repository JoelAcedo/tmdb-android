package com.jag.movies.dependencyinjector.application;

import com.jag.movies.App;
import com.jag.movies.dependencyinjector.activity.DetailActivityComponent;
import com.jag.movies.dependencyinjector.activity.DetailActivityModule;
import com.jag.movies.dependencyinjector.activity.DiscoverActivityComponent;
import com.jag.movies.dependencyinjector.activity.DiscoverActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App application);

    DiscoverActivityComponent plusDiscover(DiscoverActivityModule discoverActivityModule, DiscoverModule discoverModule);

    DetailActivityComponent plusDetail(DetailActivityModule detailActivityModule, DetailModule detailModule);
}
