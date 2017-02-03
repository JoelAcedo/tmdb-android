package com.jag.movies.dependencyinjector.activity;

import com.jag.movies.UI.Detail.DetailActivity;
import com.jag.movies.dependencyinjector.application.DetailModule;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {DetailActivityModule.class, DetailModule.class })
public interface DetailActivityComponent {

    void inject(DetailActivity activity);
}
