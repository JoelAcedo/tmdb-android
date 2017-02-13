package com.jag.movies.UI.renderes.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jag.movies.UI.Discover.DiscoverPresenter;
import com.jag.movies.R;
import com.jag.movies.Models.MovieViewModel;
import com.jag.movies.UI.Discover.fragments.MoviesPresenter;
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
 * Created by inlab on 03/02/2017.
 */

public class MovieRenderer extends RendererWithItemPosition<MovieViewModel> {

    private final Context context;
    private final MoviesPresenter presenter;
    private final ImageLoader imageLoader;
    @BindView(R.id.movie_cover_discover) ImageView movieCover;
    @BindView(R.id.movie_name_discover) TextView movieName;
    @BindView(R.id.movie_category_discover) TextView movieCategory;
    @BindView(R.id.movie_overview_discover) TextView movieOverview;
    @BindView(R.id.movie_score_discover) TextView movieScore;
    @BindView(R.id.movie_date_discover) TextView movieReleaseDate;
    @BindView(R.id.progressBarMovieCover) ProgressBar movieCoverProgressBar;

    @Inject
    public MovieRenderer(@ForActivity Context context, MoviesPresenter discoverPresenter, ImageLoader imageLoader) {
        this.context = context;
        this.presenter = discoverPresenter;
        this.imageLoader = imageLoader;
    }

    @Override
    protected void setUpView(View rootView) {}

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
        MovieViewModel movieViewModel = getContent();

        renderMovieName(movieViewModel.getTitle());
        renderMovieCategory(movieViewModel.getGenresList());
        renderMovieOverview(movieViewModel.getOverview());
        renderMovieScore(movieViewModel.getVoteAverage());
        renderMovieReleaseDate(movieViewModel.getReleaseDate());
        renderMovieCover(movieViewModel.getCoverUrl());
    }

    void renderMovieCover(String coverUrl) {
        imageLoader.bindImage(coverUrl, movieCover, movieCoverProgressBar);
    }

    void renderMovieName(String title) {
        movieName.setText(title);
    }

    void renderMovieCategory(List<String> genres) {
        String movieGenres = "";
        for (String genre : genres) {
            if (!movieGenres.isEmpty()) movieGenres = movieGenres.concat(", ");
            movieGenres = movieGenres.concat(genre);
        }
        movieCategory.setText(movieGenres);
    }

    void renderMovieOverview(String overview) {
        movieOverview.setText(overview);
    }

    void renderMovieScore(float voteAverage) {
        movieScore.setText(String.format(Locale.getDefault(), "%.01f", voteAverage));
    }

    void renderMovieReleaseDate(String releaseDate) {
        String[] date = releaseDate.split("-");
        if (date.length > 0)
            movieReleaseDate.setText(date[0]);
        else
            movieReleaseDate.setText("-");
    }

    @OnClick(R.id.list_discover_item)
    void onClickMovie() {
        MovieViewModel movieViewModel = getContent();
        presenter.movieClicked(movieViewModel.getMovieId(), getPosition(),  movieCover);
    }
}
