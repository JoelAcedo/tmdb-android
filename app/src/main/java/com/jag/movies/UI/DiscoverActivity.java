package com.jag.movies.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.jag.movies.App;
import com.jag.movies.Adapters.DiscoverMovieAdapter;
import com.jag.movies.Presenter.DiscoverPresenter;
import com.jag.movies.R;
import com.jag.movies.UI.Models.MovieViewModel;
import com.jag.movies.dependencyinjector.activity.DiscoverActivityModule;
import com.jag.movies.dependencyinjector.application.DiscoverModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import java.util.ArrayList;
import java.util.List;

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

    @Inject
    DiscoverMovieAdapter discoverMovieAdapter;

    LinearLayoutManager linearLayoutManager;

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
        presenter.onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        //discoverMovieAdapter = new DiscoverMovieAdapter(context, presenter, new GlideLoader(context));
        recyclerView.setAdapter(discoverMovieAdapter);
    }

    @Override
    public void showMovies(List<MovieViewModel> movieViewModelData) {
        discoverMovieAdapter.setMoviesData(movieViewModelData);
    }

    @Override
    public void startDetailActivity(int movieId, ImageView movieCover) {
        Intent intent = DetailActivity.getLauncherIntent(context, movieId);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                ((Activity) context), movieCover, getString(R.string.cover_transition_name));


        context.startActivity(intent, optionsCompat.toBundle());
    }
}
