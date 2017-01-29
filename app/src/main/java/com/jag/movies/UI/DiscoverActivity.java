package com.jag.movies.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.jag.movies.App;
import com.jag.movies.Adapters.DiscoverMovieAdapter;
import com.jag.movies.Presenter.DiscoverPresenter;
import com.jag.movies.R;
import com.jag.movies.dependencyinjector.activity.DiscoverActivityModule;
import com.jag.movies.dependencyinjector.application.DiscoverModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverActivity extends AppCompatActivity implements IDiscoverView {

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

        ((App) getApplication())
                .getComponent()
                .plusDiscover(new DiscoverActivityModule(this),
                        new DiscoverModule(this))
                .inject(this);

        setSupportActionBar(toolbar);
        setupRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
    public void startDetailActivity(int movieId, ImageView movieCover) {
        Intent intent = DetailActivity.getLauncherIntent(context, movieId);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                movieCover,
                "movieCover"
        );

        context.startActivity(intent, optionsCompat.toBundle());
    }
}
