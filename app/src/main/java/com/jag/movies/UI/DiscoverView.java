package com.jag.movies.UI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jag.movies.App;
import com.jag.movies.Adapters.DiscoverMovieAdapter;
import com.jag.movies.Presenter.DiscoverPresenter;
import com.jag.movies.R;
import com.jag.movies.dependencyinjector.activity.ActivityModule;
import com.jag.movies.dependencyinjector.application.DiscoverModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverView extends AppCompatActivity implements IDiscoverView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view_discover) RecyclerView recyclerView;

    @Inject
    @ForActivity
    Context context;

    @Inject
    DiscoverPresenter presenter;

    LinearLayoutManager linearLayoutManager;
    DiscoverMovieAdapter discoverMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        ButterKnife.bind(this);

        // Add dependency graph to Dagger TODO: Before or After setup?
        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new DiscoverModule(this))
                .inject(this);

        setupRecyclerView();
        presenter.onStart();
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        discoverMovieAdapter = new DiscoverMovieAdapter(context, presenter);
        recyclerView.setAdapter(discoverMovieAdapter);
    }

    @Override
    public void showMovies(ArrayList<MovieViewModel> movieData) {
        discoverMovieAdapter.setMoviesData(movieData);
    }

    @Override
    public void startDetailActivity(int movieId) {
        //TODO Intent to another activity
    }
}
