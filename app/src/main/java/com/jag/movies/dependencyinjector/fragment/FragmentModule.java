package com.jag.movies.dependencyinjector.fragment;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;

import com.jag.movies.UI.Discover.fragments.MoviesFragment;
import com.jag.movies.UI.renderes.MovieFavoriteRenderer;
import com.jag.movies.UI.renderes.MovieRenderer;
import com.jag.movies.dependencyinjector.scope.PerActivity;
import com.jag.movies.dependencyinjector.scope.PerFragment;
import com.pedrogomez.renderers.Renderer;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

/**
 * Created by joela on 12/02/2017.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @IntoSet
    @PerFragment
    public Renderer providesMovieRenderer(MovieRenderer renderer) {
        return renderer;
    }


    @Provides
    @IntoSet
    @PerFragment
    public Renderer providesMovieFavoriteRenderer(MovieFavoriteRenderer renderer) {
        return renderer;
    }
}
