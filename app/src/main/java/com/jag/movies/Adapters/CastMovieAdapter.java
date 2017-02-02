package com.jag.movies.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jag.movies.Presenter.DetailPresenter;
import com.jag.movies.R;
import com.jag.movies.UI.Models.ActorViewModel;
import com.jag.movies.Utils.ImageLoader;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CastMovieAdapter extends RecyclerView.Adapter<CastMovieAdapter.ActorHolder> {

    private final DetailPresenter detailPresenter;
    private final ImageLoader imageLoader;
    private final Context context;
    private List<ActorViewModel> castData;
    private int vibrantColor;

    @Inject
    public CastMovieAdapter(@ForActivity Context context, DetailPresenter detailPresenter, ImageLoader imageLoader) {
        this.detailPresenter = detailPresenter;
        this.imageLoader = imageLoader;
        castData = new ArrayList<>();
        this.context = context;
        vibrantColor = context.getResources().getColor(R.color.colorPrimary);
    }

    @Override
    public ActorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cast_item, parent, false);

        return new ActorHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorHolder holder, int position) {
        holder.renderActorImage(castData.get(position).getProfileUrl());
        holder.renderActorName(castData.get(position).getName());
        holder.renderCharacterName(castData.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return castData.size();
    }

    public void setCastData(List<ActorViewModel> castData) {
        this.castData = castData;
        notifyDataSetChanged();
    }

    public void setVibrantColor(int vibrantColor) {
        this.vibrantColor = vibrantColor;
        notifyDataSetChanged();
        // TODO Actualizar las vistas que ya estan pintadas, para que se pinten con el color vibrant
    }


    class ActorHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.actor_image) RoundedImageView actorImage;
        @BindView(R.id.actor_name) TextView actorName;
        @BindView(R.id.character_name) TextView characterName;

        ActorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void renderActorImage(String profileUrl) {
            //TODO Push images
            imageLoader.bindImage(profileUrl, actorImage, R.drawable.ic_account_grey600_48dp);
            actorImage.setBorderColor(vibrantColor);
        }

        void renderActorName(String actorName) {
            this.actorName.setText(actorName);
            this.actorName.setTextColor(vibrantColor);
        }

        void renderCharacterName(String characterName) {
            this.characterName.setText(characterName);
        }
    }
}
