package com.jag.movies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jag.movies.Presenter.DiscoverPresenter;
import com.jag.movies.R;
import com.jag.movies.UI.MovieViewModel;
import com.jag.movies.dependencyinjector.scope.PerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverMovieAdapter extends RecyclerView.Adapter<DiscoverMovieAdapter.MovieHolder> {

    private final Context context;
    private List<MovieViewModel> movieDataset;
    private final DiscoverPresenter presenter;

    // TODO: intentar injectar con Dagger pasando el context del activity
    public DiscoverMovieAdapter(Context context, DiscoverPresenter discoverPresenter) {
        this.movieDataset = new ArrayList<>();
        this.context = context;
        presenter = discoverPresenter;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_discover, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.renderMovieName(movieDataset.get(position).getTitle());
        holder.renderMovieCategory(movieDataset.get(position).getGenresList());
        holder.renderMovieOverview(movieDataset.get(position).getOverview());
        holder.renderMovieScore(movieDataset.get(position).getVoteAverage());
        holder.renderMovieReleaseDate(movieDataset.get(position).getReleaseDate());
        holder.renderMovieCover(movieDataset.get(position).getCoverUrl());

        holder.bind(movieDataset.get(position).getMovieId());
    }

    @Override
    public int getItemCount() {
        return movieDataset.size();
    }

    public void setMoviesData(ArrayList<MovieViewModel> moviesData) {
        this.movieDataset = moviesData;
        notifyDataSetChanged();
    }


    class MovieHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_cover_discover) ImageView movieCover;
        @BindView(R.id.movie_name_discover) TextView movieName;
        @BindView(R.id.movie_category_discover) TextView movieCategory;
        @BindView(R.id.movie_overview_discover) TextView movieOverview;
        @BindView(R.id.movie_score_discover) TextView movieScore;
        @BindView(R.id.movie_date_discover) TextView movieReleaseDate;

        MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int movieId) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.movieClicked(movieId);
                }
            });
        }

        void renderMovieCover(String coverUrl) {
            Glide.with(context)
                    .load(coverUrl)
                    .into(movieCover);
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
    }
}
