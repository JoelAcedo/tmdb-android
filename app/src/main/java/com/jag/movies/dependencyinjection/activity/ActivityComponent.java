package com.jag.movies.dependencyinjection.activity;

/**
 * Created by Albert.Ruiz on 26/01/2017.
 */

import com.jag.movies.dependencyinjection.application.ViewModule;
import com.jag.movies.dependencyinjection.scope.PerActivity;
import com.jag.movies.main.UI.DiscoverView;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, ViewModule.class})
public interface ActivityComponent {


    void inject(DiscoverView activity);
}