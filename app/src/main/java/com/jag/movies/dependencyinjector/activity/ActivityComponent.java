package com.jag.movies.dependencyinjector.activity;

import com.jag.movies.UI.Detail.DetailActivity;
import com.jag.movies.UI.Discover.DiscoverActivity;
import com.jag.movies.dependencyinjector.application.ViewModule;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, ViewModule.class })
public interface ActivityComponent {

    void inject(DetailActivity activity);

    void inject(DiscoverActivity activity);
}
