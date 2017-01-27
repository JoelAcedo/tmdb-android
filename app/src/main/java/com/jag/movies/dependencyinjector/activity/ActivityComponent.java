package com.jag.movies.dependencyinjector.activity;

import com.jag.movies.UI.DiscoverView;
import com.jag.movies.dependencyinjector.application.DiscoverModule;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, DiscoverModule.class})
public interface ActivityComponent {

    void inject(DiscoverView activity);
}
