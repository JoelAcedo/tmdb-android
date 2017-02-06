package com.jag.movies.UI.Discover;

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
import com.jag.movies.R;
import com.jag.movies.UI.Detail.DetailActivity;
import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.UI.renderes.MovieRendererBuilder;
import com.jag.movies.UI.renderes.RendererAdapterWithItemPosition;
import com.jag.movies.Utils.EndlessRecyclerViewScrollListener;
import com.jag.movies.dependencyinjector.activity.ActivityModule;
import com.jag.movies.dependencyinjector.application.ViewModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverActivity extends AppCompatActivity implements DiscoverView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view_discover) RecyclerView recyclerView;

    @Inject
    @ForActivity
    Context context;

    @Inject
    DiscoverPresenter presenter;

    LinearLayoutManager linearLayoutManager;

    @Inject
    MovieRendererBuilder movieRendererBuilder;

    RVRendererAdapter<MovieViewModel> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        ButterKnife.bind(this);

        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);

        setSupportActionBar(toolbar);
        setupRecyclerView();
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }


    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onLoadMore(page);
            }
        });

        //discoverMovieAdapter = new DiscoverMovieAdapter(context, presenter, new GlideLoader(context));
        //recyclerView.setAdapter(discoverMovieAdapter);
    }

    @Override
    public void showMovies(List<MovieViewModel> movieViewModelData) {
        ListAdapteeCollection<MovieViewModel> movies = new ListAdapteeCollection<>(movieViewModelData);
        adapter = new RendererAdapterWithItemPosition<MovieViewModel>(movieRendererBuilder, movies);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateMovieFavoritedState(MovieViewModel movie, int position) {
        adapter.getItem(position).setFavorite(movie.isFavorited());
        adapter.notifyItemChanged(position);
    }

    @Override
    public void addMovies(List<MovieViewModel> movieViewModelData) {
        ListAdapteeCollection<MovieViewModel> movies = new ListAdapteeCollection<>(movieViewModelData);
        adapter.addAll(movies);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void startDetailActivity(int movieId, ImageView movieCover) {
        Intent intent = DetailActivity.getLauncherIntent(context, movieId);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                ((Activity) context), movieCover, getString(R.string.cover_transition_name));


        context.startActivity(intent, optionsCompat.toBundle());
    }
}
