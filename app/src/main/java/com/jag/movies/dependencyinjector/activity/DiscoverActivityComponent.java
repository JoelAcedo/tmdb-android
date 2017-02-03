package com.jag.movies.dependencyinjector.activity;

import com.jag.movies.UI.Discover.DiscoverActivity;
import com.jag.movies.dependencyinjector.application.DiscoverModule;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {DiscoverActivityModule.class, DiscoverModule.class})
public interface DiscoverActivityComponent {

    void inject(DiscoverActivity activity);
}
