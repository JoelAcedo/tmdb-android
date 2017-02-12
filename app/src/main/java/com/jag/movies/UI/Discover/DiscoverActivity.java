package com.jag.movies.UI.Discover;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.test.mock.MockApplication;

import com.jag.movies.Adapters.DiscoverFragmentAdapter;
import com.jag.movies.App;
import com.jag.movies.R;
import com.jag.movies.UI.Detail.DetailActivity;
import com.jag.movies.UI.Discover.fragments.MoviesFragment;
import com.jag.movies.UI.Discover.fragments.TvShowsFragment;
import com.jag.movies.UI.renderes.MovieRendererBuilder;
import com.jag.movies.dependencyinjector.activity.ActivityComponent;
import com.jag.movies.dependencyinjector.activity.ActivityModule;
import com.jag.movies.dependencyinjector.activity.DaggerActivityComponent;
import com.jag.movies.dependencyinjector.application.ViewModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverActivity extends AppCompatActivity implements DiscoverView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager_discover) ViewPager viewPager;
    @BindView(R.id.tabs_discover) TabLayout tabLayout;

    @Inject
    @ForActivity
    Context context;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        ButterKnife.bind(this);

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .viewModule(new ViewModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();
        activityComponent.inject(this);

        setSupportActionBar(toolbar);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager() {
        DiscoverFragmentAdapter fragmentAdapter = new DiscoverFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new MoviesFragment(), getString(R.string.movie_fragment_title));
        fragmentAdapter.addFragment(new TvShowsFragment(), getString(R.string.tv_shows_fragment_title));
        viewPager.setAdapter(fragmentAdapter);
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    /*    private void setupRecyclerView() {
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
    }*/


}
