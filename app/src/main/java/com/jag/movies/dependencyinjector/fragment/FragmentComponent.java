package com.jag.movies.dependencyinjector.fragment;

import com.jag.movies.UI.Discover.fragments.MoviesFragment;
import com.jag.movies.dependencyinjector.activity.ActivityComponent;
import com.jag.movies.dependencyinjector.activity.ActivityModule;
import com.jag.movies.dependencyinjector.application.ViewModule;
import com.jag.movies.dependencyinjector.scope.PerActivity;
import com.jag.movies.dependencyinjector.scope.PerFragment;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by joela on 12/02/2017.
 */

@PerFragment
@Component(dependencies = {ActivityComponent.class}, modules = {FragmentModule.class, FragmentViewModule.class })
public interface FragmentComponent {

    void inject(MoviesFragment fragment);

}
