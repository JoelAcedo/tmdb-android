package com.jag.movies.dependencyinjector.fragment;

import android.support.v4.app.Fragment;

import com.example.entities.TvShow;
import com.jag.movies.UI.renderes.movies.MovieFavoriteRenderer;
import com.jag.movies.UI.renderes.movies.MovieRenderer;
import com.jag.movies.UI.renderes.tvShows.TvShowFavoriteRenderer;
import com.jag.movies.UI.renderes.tvShows.TvShowRenderer;
import com.jag.movies.dependencyinjector.qualifier.ForMovies;
import com.jag.movies.dependencyinjector.qualifier.ForTvShows;
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
    @ForMovies
    @PerFragment
    public Renderer providesMovieRenderer(MovieRenderer renderer) {
        return renderer;
    }


    @Provides
    @IntoSet
    @ForMovies
    @PerFragment
    public Renderer providesMovieFavoriteRenderer(MovieFavoriteRenderer renderer) {
        return renderer;
    }

    @Provides
    @IntoSet
    @ForTvShows
    @PerFragment
    public Renderer providesTvShowRenderer(TvShowRenderer renderer) {
        return renderer;
    }


    @Provides
    @IntoSet
    @ForTvShows
    @PerFragment
    public Renderer providesTvShowFavoriteRenderer(TvShowFavoriteRenderer renderer) {
        return renderer;
    }
}
