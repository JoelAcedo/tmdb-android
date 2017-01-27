package com.jag.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jag.movies.Presenter.DiscoverPresenter;
import com.jag.movies.UI.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by inlab on 27/01/2017.
 */

public class DiscoverMovieAdapter extends RecyclerView.Adapter<DiscoverMovieAdapter.MovieHolder> {

    private List<MovieViewModel> dataset;
    private final DiscoverPresenter presenter;

    @Inject
    public DiscoverMovieAdapter(DiscoverPresenter discoverPresenter) {
        this.dataset = new ArrayList<>();
        presenter = discoverPresenter;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_discover, parent, false);

        MovieHolder movieHolder = new MovieHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.movieCover.setImageBitmap(dataset.get(position).getCover());
        holder.movieName.setText(dataset.get(position).getTitle());

        List<String> genres = dataset.get(position).getGenresList();
        holder.renderMovieCategory(genres);
//        holder.renderMovieCover(dataset.get(position).getCoverUrl());

        holder.movieCover.setImageBitmap(dataset.get(position).getCover());
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setMoviesData(ArrayList<MovieViewModel> moviesData) {
        this.dataset = moviesData;
        notifyDataSetChanged();
    }


    public class MovieHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_cover_discover)
        ImageView movieCover;
        @BindView(R.id.movie_name_discover)
        TextView movieName;
        @BindView(R.id.movie_category_discover)
        TextView movieCategory;

        public MovieHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.movieClicked(dataset.get(position).getMovieId());
                }
            });
        }

        public void renderMovieCategory(List<String> genres) {
            String movieGenres = "";
            for (String genre : genres) {
                if (!movieGenres.isEmpty()) movieGenres = movieGenres.concat(", ");
                movieGenres = movieGenres.concat(genre);
            }
            movieCategory.setText(movieGenres);
        }
    }
}
