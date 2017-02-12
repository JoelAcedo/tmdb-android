package com.jag.movies.UI.Discover.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.R;
import com.jag.movies.UI.Detail.DetailActivity;
import com.jag.movies.UI.Discover.DiscoverActivity;
import com.jag.movies.UI.renderes.movies.MovieRendererBuilder;
import com.jag.movies.UI.renderes.RendererAdapterWithItemPosition;
import com.jag.movies.Utils.EndlessRecyclerViewScrollListener;
import com.jag.movies.dependencyinjector.fragment.DaggerFragmentComponent;
import com.jag.movies.dependencyinjector.fragment.FragmentComponent;
import com.jag.movies.dependencyinjector.fragment.FragmentModule;
import com.jag.movies.dependencyinjector.fragment.FragmentViewModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joela on 11/02/2017.
 */

public class MoviesFragment extends Fragment implements FragmentDiscoverView<MovieViewModel> {

    @BindView(R.id.recycler_view_discover)
    RecyclerView recyclerView;

    private FragmentActivity fActivity;
    private LinearLayout fLayout;
    private LinearLayoutManager linearLayoutManager;
    private RVRendererAdapter<MovieViewModel> adapter;

    @Inject
    MoviesPresenter presenter;

    @Inject
    MovieRendererBuilder movieRendererBuilder;

    @Inject
    @ForActivity
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
        FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .fragmentViewModule(new FragmentViewModule(this))
                .activityComponent(((DiscoverActivity) getActivity()).getActivityComponent())
                .build();
        fragmentComponent.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fActivity = ((FragmentActivity) super.getActivity());
        fLayout = ((LinearLayout) inflater.inflate(R.layout.fragment_discover, container, false));

        ButterKnife.bind(this, fLayout);

        setupRecyclerView();
        return fLayout;
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
    }

    @Override
    public void showData(List<MovieViewModel> movieViewModelData) {
        ListAdapteeCollection<MovieViewModel> movies = new ListAdapteeCollection<>(movieViewModelData);
        adapter = new RendererAdapterWithItemPosition<>(movieRendererBuilder, movies);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateItemFavoritedState(MovieViewModel movie, int position) {
        adapter.getItem(position).setFavorite(movie.isFavorited());
        adapter.notifyItemChanged(position);
    }

    @Override
    public void addData(List<MovieViewModel> movieViewModelData) {
        ListAdapteeCollection<MovieViewModel> movies = new ListAdapteeCollection<>(movieViewModelData);
        adapter.addAll(movies);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void startDetailActivity(int itemId, ImageView cover) {
        Intent intent = DetailActivity.getLauncherIntent(context, itemId);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                ((Activity) context), cover, getString(R.string.cover_transition_name));

        context.startActivity(intent, optionsCompat.toBundle());
    }
}
