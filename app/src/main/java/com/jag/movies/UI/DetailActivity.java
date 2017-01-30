package com.jag.movies.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.jag.movies.App;
import com.jag.movies.Presenter.DetailPresenter;
import com.jag.movies.R;
import com.jag.movies.dependencyinjector.activity.DetailActivityModule;
import com.jag.movies.dependencyinjector.application.DetailModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.jag.movies.Utils.ImageLoader;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity implements IDetailView {

    @BindView(R.id.collapsingToolbar_detail) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbar_detail) AppBarLayout appBarLayout;
    @BindView(R.id.toolbar_detail) Toolbar toolbar;
    @BindView(R.id.movie_info_detail) RelativeLayout movieInfo;
    @BindView(R.id.imageToolbar_detail) ImageView movieCover;
    @BindView(R.id.scroll_detail) NestedScrollView nestedScrollView;
    @BindView(R.id.fab_detail) FloatingActionButton floatingButton;
    @BindView(R.id.movie_name_detail) TextView movieName;
    @BindView(R.id.movie_genres_detail) TextView movieGenres;
    @BindView(R.id.movie_score_detail) TextView movieScore;
    @BindView(R.id.movie_date_detail) TextView movieReleaseDate;
    @BindView(R.id.movie_overview_detail) TextView movieOverview;

    @Inject
    @ForActivity
    Context context;

    @Inject
    DetailPresenter detailPresenter;

    @Inject
    ImageLoader imageLoader;

    public final static String ID_MOVIE = "movieId";

    public static Intent getLauncherIntent(Context context, int movieId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(ID_MOVIE, movieId);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        ((App) getApplication())
                .getComponent()
                .plusDetail(new DetailActivityModule(this),
                        new DetailModule(this))
                .inject(this);

        setupAnimation();
        setupToolbar();
        setupFloatingButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO get intent in activity or at presenter
        detailPresenter.onStart(getIntent());
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(" ");
    }

    private void setupAnimation() {
        supportPostponeEnterTransition();
        movieCover.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (movieCover.getDrawable() != null) {
                    movieCover.getViewTreeObserver().removeOnPreDrawListener(this);
                    renderToolbarColor();
                    supportStartPostponedEnterTransition();
                    return true;
                }
                return false;
            }
        });
    }

    private void setupFloatingButton() {
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailPresenter.floatingButtonClicked();
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
        imageLoader.bindImage(coverUrl, movieCover);
    }

    @Override
    public void renderToolbarColor() {
        Palette palette = Palette.from(imageLoader.getBitmap(movieCover)).generate();

        int defaultColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int vibrantColor = palette.getVibrantColor(defaultColor);

        collapsingToolbarLayout.setStatusBarScrimColor(vibrantColor);
        collapsingToolbarLayout.setContentScrimColor(vibrantColor);

        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        movieInfo.setBackgroundColor(vibrantColor);
    }

    @Override
    public void renderTitle(String title) {
        movieName.setText(title);
    }

    @Override
    public void renderOverview(String overview) {
        movieOverview.setText(overview);
    }

    @Override
    public void renderGenres(List<String> genresList) {
        String movieGenres = "";
        for (String genre : genresList) {
            if (!movieGenres.isEmpty()) movieGenres = movieGenres.concat(", ");
            movieGenres = movieGenres.concat(genre);
        }

        this.movieGenres.setText(movieGenres);
    }

    @Override
    public void renderScore(float voteAverage) {
        movieScore.setText(String.format(Locale.getDefault(), "%.01f", voteAverage));
    }

    @Override
    public void renderReleaseDate(String releaseDate) {
        String[] date = releaseDate.split("-");
        if (date.length > 0)
            movieReleaseDate.setText(date[0]);
        else
            movieReleaseDate.setText("-");
    }
}

