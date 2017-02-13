package com.jag.movies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jag.movies.Models.EpisodeViewModel;
import com.jag.movies.R;
import com.jag.movies.UI.Detail.tvshow.TvShowDetailPresenter;
import com.jag.movies.Utils.ImageLoader;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joela on 13/02/2017.
 */

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodeHolder> {

    private final TvShowDetailPresenter presenter;
    private final ImageLoader imageLoader;
    private final Context context;
    private List<EpisodeViewModel> episodesData;
    private int vibrantColor;

    @Inject
    public EpisodesAdapter(@ForActivity Context context, TvShowDetailPresenter presenter,
                           ImageLoader imageLoader) {
        this.presenter = presenter;
        this.imageLoader = imageLoader;
        episodesData = new ArrayList<>();
        this.context = context;
        vibrantColor = context.getResources().getColor(R.color.colorPrimary);
    }

    @Override
    public EpisodeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_episode_item, parent, false);

        return new EpisodeHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodeHolder holder, int position) {
        holder.renderEpisodeNumber(episodesData.get(position).getEpisodeNumber());
        holder.renderEpisodeName(episodesData.get(position).getName());
        holder.renderEpisodeReleaseDate(episodesData.get(position).getReleaseDate());
        holder.renderEpisodeOverview(episodesData.get(position).getOverview());
        holder.renderImageCover(episodesData.get(position).getPosterPath());
        holder.renderColor();
    }

    @Override
    public int getItemCount() {
        return episodesData.size();
    }

    public void setEpisodesData(List<EpisodeViewModel> episodesData) {
        this.episodesData = episodesData;
        notifyDataSetChanged();
    }

    public void setVibrantColor(int vibrantColor) {
        this.vibrantColor = vibrantColor;
        notifyDataSetChanged();
    }

    public class EpisodeHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.episode_number) TextView episodeNumber;
        @BindView(R.id.episode_name) TextView episodeName;
        @BindView(R.id.episode_date) TextView episodeDate;
        @BindView(R.id.episode_overview) TextView episodeOverview;
        @BindView(R.id.episode_cover) ImageView episodeCover;
        @BindView(R.id.progressBarEpisodeCover) ProgressBar episodeProgresBar;

        public EpisodeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void renderEpisodeNumber(int episodeNumber) {
            this.episodeNumber.setText(episodeNumber);
        }

        public void renderEpisodeName(String name) {
            this.episodeName.setText(name);
        }

        public void renderEpisodeReleaseDate(String releaseDate) {
            //TODO format data
        }

        public void renderEpisodeOverview(String overview) {
            this.episodeOverview.setText(overview);
        }

        public void renderImageCover(String posterPath) {
            imageLoader.bindImage(posterPath, episodeCover, episodeProgresBar);
        }

        public void renderColor() {
            this.episodeNumber.setTextColor(vibrantColor);
            this.episodeName.setTextColor(vibrantColor);
        }
    }
}
