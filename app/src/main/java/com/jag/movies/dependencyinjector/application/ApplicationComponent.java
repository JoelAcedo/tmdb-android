package com.jag.movies.dependencyinjector.application;

import android.content.Context;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repositories.CastRepository;
import com.example.repositories.MovieRepository;
import com.example.repositories.TvShowRepository;
import com.jag.movies.App;
import com.jag.movies.Utils.ImageLoader;
import com.jag.movies.dependencyinjector.activity.ActivityComponent;
import com.jag.movies.dependencyinjector.activity.ActivityModule;
import com.jag.movies.dependencyinjector.fragment.FragmentComponent;
import com.jag.movies.dependencyinjector.fragment.FragmentModule;
import com.jag.movies.dependencyinjector.qualifier.ForApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App application);

    @ForApp
    Context getAppContext();
    ImageLoader getImageLoader();
    ThreadExecutor getThreadExecutor();
    PostExecutionThread getPostExecutionThread();

    MovieRepository getMovieRepository();
    CastRepository getCastRepository();
    TvShowRepository getTvShowRepository();

//    ActivityComponent plus(ActivityModule activityModule, ViewModule viewModule);

//    FragmentComponent plus(FragmentModule fragmentModule, ViewModule viewModule);
}
