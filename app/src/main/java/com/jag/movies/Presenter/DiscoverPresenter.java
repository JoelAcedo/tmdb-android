package com.jag.movies.Presenter;

import com.jag.movies.Model.DiscoverModel;
import com.jag.movies.UI.DiscoverView;
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

    public void movieClicked(int movieId) {

    }

    public void onStart() {
        // TODO Get movie data
        // .....
        ArrayList<MovieViewModel> movies = new ArrayList<>();

        discoverView.showMovies(movies);
    }
}
