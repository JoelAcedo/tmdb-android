package com.jag.movies.dependencyinjector.application;

import com.jag.movies.UI.Detail.DetailView;
import com.jag.movies.UI.Detail.movie.MovieDetailView;
import com.jag.movies.UI.Detail.tvshow.TvShowDetailView;
import com.jag.movies.UI.Discover.DiscoverView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private DiscoverView discoverView;
    private DetailView detailView;
    private MovieDetailView movieDetailView;
    private TvShowDetailView tvShowDetailView;

    public ViewModule(DiscoverView discoverView) {
        this.discoverView = discoverView;
    }

    public ViewModule(DetailView detailView) {
        this.detailView = detailView;
    }

    public ViewModule(MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
    }

    public ViewModule(TvShowDetailView tvShowDetailView) {
        this.tvShowDetailView = tvShowDetailView;
    }

    @Provides
    DiscoverView providesDiscoverView() {
        return discoverView;
    }

    @Provides
    DetailView providesDetailView() {
        return detailView;
    }

    @Provides
    public MovieDetailView getMovieDetailView() {
        return movieDetailView;
    }
}
