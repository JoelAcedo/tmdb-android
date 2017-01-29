package com.jag.movies.Presenter;

import android.content.Intent;
import android.media.Image;
import android.widget.ImageView;

import com.jag.movies.Model.DataSource.MovieFakeDataSource;
import com.jag.movies.Model.DiscoverModel;
import com.jag.movies.UI.DetailActivity;
import com.jag.movies.UI.IDiscoverView;
import com.jag.movies.UI.MovieViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import java.util.ArrayList;

import javax.inject.Inject;

@PerActivity
public class DiscoverPresenter {

    private final IDiscoverView discoverView;
    private final DiscoverModel discoverModel;

    @Inject
    public DiscoverPresenter(IDiscoverView discoverView, DiscoverModel discoverModel) {
        this.discoverView = discoverView;
        this.discoverModel = discoverModel;
    }

    public void movieClicked(int movieId, ImageView movieCover) {
        discoverView.startDetailActivity(movieId, movieCover);
    }

    public void onStart() {
        // TODO Get movie data
        // .....
        ArrayList<MovieViewModel> movies = new MovieFakeDataSource().getData();
        discoverView.showMovies(movies);
    }
}
