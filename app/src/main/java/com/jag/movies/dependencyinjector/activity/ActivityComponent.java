package com.jag.movies.dependencyinjector.activity;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.MovieRepository;
import com.example.repositories.TvShowRepository;
import com.jag.movies.UI.Detail.DetailActivity;
import com.jag.movies.UI.Discover.DiscoverActivity;
import com.jag.movies.Utils.ImageLoader;
import com.jag.movies.dependencyinjector.application.ApplicationComponent;
import com.jag.movies.dependencyinjector.application.ViewModule;
import com.jag.movies.dependencyinjector.fragment.FragmentComponent;
import com.jag.movies.dependencyinjector.fragment.FragmentModule;
import com.jag.movies.dependencyinjector.fragment.FragmentViewModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class, ViewModule.class })
public interface ActivityComponent {

    void inject(DetailActivity activity);

    void inject(DiscoverActivity activity);

    @ForActivity
    Context getActivityContext();
    ImageLoader getImageLoader();
    ThreadExecutor getThreadExecutor();
    PostExecutionThread getPostExecutionThread();

    MovieRepository getMovieRepository();
    TvShowRepository getTvShowRepository();
}
