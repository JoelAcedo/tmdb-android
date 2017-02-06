package com.jag.movies.dependencyinjector.activity;

import android.app.Activity;
import android.content.Context;

import com.jag.movies.UI.Detail.DetailActivity;
import com.jag.movies.UI.Discover.DiscoverActivity;
import com.jag.movies.UI.renderes.MovieFavoriteRenderer;
import com.jag.movies.UI.renderes.MovieRenderer;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.jag.movies.dependencyinjector.scope.PerActivity;
import com.pedrogomez.renderers.Renderer;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @ForActivity
    Context providesContext() {
        return activity;
    }

    @Provides
    @IntoSet
    @PerActivity
    public Renderer providesRenderer(MovieRenderer renderer) {
        return renderer;
    }


    @Provides
    @IntoSet
    @PerActivity
    public Renderer providesFavoriteRenderer(MovieFavoriteRenderer renderer) {
        return renderer;
    }
}
