package com.jag.movies.UI.renderes.tvShows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.Models.TvShowViewModel;
import com.jag.movies.R;
import com.jag.movies.UI.Discover.fragments.MoviesPresenter;
import com.jag.movies.UI.Discover.fragments.TvShowsPresenter;
import com.jag.movies.UI.renderes.RendererWithItemPosition;
import com.jag.movies.Utils.ImageLoader;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by joela on 12/02/2017.
 */

public class TvShowRenderer extends RendererWithItemPosition<TvShowViewModel> {

    private final Context context;
    private final TvShowsPresenter presenter;
    private final ImageLoader imageLoader;
    @BindView(R.id.movie_cover_discover) ImageView tvShowCover;
    @BindView(R.id.movie_name_discover) TextView tvShoweName;
    @BindView(R.id.movie_category_discover) TextView tvShoweCategory;
    @BindView(R.id.movie_overview_discover) TextView tvShoweOverview;
    @BindView(R.id.movie_score_discover) TextView tvShoweScore;
    @BindView(R.id.movie_date_discover) TextView tvShoweReleaseDate;
    @BindView(R.id.progressBarMovieCover) ProgressBar tvShoweCoverProgressBar;

    @Inject
    public TvShowRenderer(@ForActivity Context context, TvShowsPresenter presenter, ImageLoader imageLoader) {
        this.context = context;
        this.presenter = presenter;
        this.imageLoader = imageLoader;
    }

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_discover_item, parent, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void render() {
        TvShowViewModel tvShowViewModel = getContent();

        renderTvShowName(tvShowViewModel.getName());
        renderTvShowCategory(tvShowViewModel.getGenresList());
        renderTvShowOverview(tvShowViewModel.getOverview());
        renderTvShowScore(tvShowViewModel.getVoteAverage());
        renderTvShowReleaseDate(tvShowViewModel.getReleaseDate());
        renderTvShowCover(tvShowViewModel.getCoverUrl());
    }

    private void renderTvShowReleaseDate(String releaseDate) {
        String[] date = releaseDate.split("-");
        if (date.length > 0)
            tvShoweReleaseDate.setText(date[0]);
        else
            tvShoweReleaseDate.setText("-");
    }

    private void renderTvShowCover(String coverUrl) {
        imageLoader.bindImage(coverUrl, tvShowCover, tvShoweCoverProgressBar);
    }

    private void renderTvShowScore(float voteAverage) {
        tvShoweScore.setText(String.format(Locale.getDefault(), "%.01f", voteAverage));
    }

    private void renderTvShowOverview(String overview) {
        tvShoweOverview.setText(overview);
    }

    private void renderTvShowCategory(List<String> genres) {
        String tvShowGenres = "";
        for (String genre : genres) {
            if (!tvShowGenres.isEmpty()) tvShowGenres = tvShowGenres.concat(", ");
            tvShowGenres = tvShowGenres.concat(genre);
        }
        tvShoweCategory.setText(tvShowGenres);
    }

    private void renderTvShowName(String name) {
        tvShoweName.setText(name);
    }

    @OnClick(R.id.list_discover_item)
    void onClickMovie() {
        TvShowViewModel tvShowViewModel = getContent();
        presenter.tvShowClicked(tvShowViewModel.getTvShowId(), getPosition(), tvShowCover);
    }
}
