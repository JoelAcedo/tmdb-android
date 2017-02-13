package com.jag.movies.UI.Detail.tvshow;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.jag.movies.Adapters.EpisodesAdapter;
import com.jag.movies.App;
import com.jag.movies.Models.EpisodeViewModel;
import com.jag.movies.Models.SeasonViewModel;
import com.jag.movies.R;
import com.jag.movies.Utils.ImageLoader;
import com.jag.movies.dependencyinjector.activity.ActivityComponent;
import com.jag.movies.dependencyinjector.activity.ActivityModule;
import com.jag.movies.dependencyinjector.activity.DaggerActivityComponent;
import com.jag.movies.dependencyinjector.application.ViewModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joela on 12/02/2017.
 */

public class TvShowDetailActivity extends AppCompatActivity implements TvShowDetailView {

    @BindView(R.id.collapsingToolbar_detail) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar_detail) Toolbar toolbar;
    @BindView(R.id.item_info_detail) RelativeLayout tvShowInfo;
    @BindView(R.id.imageToolbar_detail) ImageView tvShowCover;
    @BindView(R.id.fab_detail) FloatingActionButton floatingButton;
    @BindView(R.id.item_name_detail) TextView tvShowName;
    @BindView(R.id.item_genres_detail) TextView tvShowGenres;
    @BindView(R.id.item_score_detail) TextView tvShowScore;
    @BindView(R.id.item_date_detail) TextView tvShowReleaseDate;
    @BindView(R.id.tvshow_overview_detail) TextView tvShowOverview;
    @BindView(R.id.tvshow_episodes_title) TextView episodeTitle;
    @BindView(R.id.tvshow_season_spinn) Spinner seasonSpinn;

    @BindView(R.id.recycler_view_tvshow_episodes_detail) RecyclerView episodesList;


    @Inject
    @ForActivity
    Context context;

    @Inject
    TvShowDetailPresenter tvShowDetailPresenter;

    @Inject
    EpisodesAdapter episodesAdapter;

    @Inject
    ImageLoader imageLoader;

    Palette palette;

    LinearLayoutManager linearLayoutManager;


    public final static String ID_TVSHOW = "tvShowId";

    public static Intent getLauncherIntent(Context context, int movieId) {
        Intent intent = new Intent(context, TvShowDetailActivity.class);
        intent.putExtra(ID_TVSHOW, movieId);

        return intent;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tvshow_detail);

        ButterKnife.bind(this);

        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .viewModule(new ViewModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();
        activityComponent.inject(this);

        setupAnimation();
        setupToolbar();
        setupFloatingButton();
        setupEpisodeRecyclerView();

        tvShowDetailPresenter.onStart(getIntent());
    }

    private void setupEpisodeRecyclerView() {
        episodesList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        episodesList.setLayoutManager(linearLayoutManager);
        episodesList.setNestedScrollingEnabled(false);
        episodesList.setAdapter(episodesAdapter);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(" ");
    }

    private void setupAnimation() {
        //TODO Revisar que pasa sin network
        if (isNetworkAvailable()) {
            supportPostponeEnterTransition();
            tvShowCover.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    if (tvShowCover.getDrawable() != null) {
                        tvShowCover.getViewTreeObserver().removeOnPreDrawListener(this);
                        computePalette(tvShowCover);
                        supportStartPostponedEnterTransition();
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    private void setupFloatingButton() {
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowDetailPresenter.floatingButtonClicked();
            }
        });
    }

    @Override
    public void setFloatingButtonNotFavorited() {
        floatingButton.setImageResource(R.drawable.ic_heart_outline_white_48dp);
    }

    @Override
    public void setFloatingButtonFavorited() {
        floatingButton.setImageResource(R.drawable.ic_heart_white_48dp);
    }

    @Override
    public void renderCover(String coverUrl) {
        imageLoader.bindImage(coverUrl, tvShowCover);
    }

    @Override
    public void renderTitle(String title) {
        tvShowName.setText(title);
    }

    @Override
    public void renderOverview(String overview) {
        tvShowOverview.setText(overview);
    }

    @Override
    public void renderGenres(List<String> genresList) {
        String tvShowGenres = "";
        for (String genre : genresList) {
            if (!tvShowGenres.isEmpty()) tvShowGenres = tvShowGenres.concat(", ");
            tvShowGenres = tvShowGenres.concat(genre);
        }

        this.tvShowGenres.setText(tvShowGenres);
    }

    @Override
    public void renderScore(float voteAverage) {
        tvShowScore.setText(String.format(Locale.getDefault(), "%.01f", voteAverage));
    }

    @Override
    public void renderReleaseDate(String releaseDate) {
        String[] date = releaseDate.split("-");
        if (date.length > 0)
            tvShowReleaseDate.setText(date[0]);
        else
            tvShowReleaseDate.setText("-");
    }

    @Override
    public void computePalette(ImageView imageView) {
        Palette.from(imageLoader.getBitmap(tvShowCover)).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                TvShowDetailActivity.this.palette = palette;
                int defaultColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                tvShowDetailPresenter.updateVibrantColor(palette.getVibrantColor(defaultColor));
            }
        });
    }

    @Override
    public void renderColors(int vibrantColor) {
        collapsingToolbarLayout.setStatusBarScrimColor(vibrantColor);
        collapsingToolbarLayout.setContentScrimColor(vibrantColor);

        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        tvShowInfo.setBackgroundColor(vibrantColor);
        episodeTitle.setTextColor(vibrantColor);
        episodesAdapter.setVibrantColor(vibrantColor);
    }


    @Override
    public void showSeasons(List<SeasonViewModel> seasonData) {
        ArrayList seasonNames = new ArrayList();
        for (SeasonViewModel seasonViewModel : seasonData) {
            seasonNames.add(getString(R.string.season_pre) + " " + String.valueOf(seasonViewModel.getSeasonNumber() + 1) /*+ "("
                + seasonViewModel.getEpisodeCount() + ")"*/);
        }

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, seasonNames);
        seasonSpinn.setAdapter(spinnerAdapter);
        seasonSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvShowDetailPresenter.seasonSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void showEpisodes(List<EpisodeViewModel> episodesData) {
        episodesAdapter.setEpisodesData(episodesData);
    }
}
