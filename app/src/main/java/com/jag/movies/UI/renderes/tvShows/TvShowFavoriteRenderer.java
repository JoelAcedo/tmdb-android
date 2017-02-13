package com.jag.movies.UI.renderes.tvShows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jag.movies.R;
import com.jag.movies.UI.Discover.fragments.TvShowsPresenter;
import com.jag.movies.Utils.ImageLoader;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by joela on 12/02/2017.
 */

public class TvShowFavoriteRenderer extends TvShowRenderer {

    @Inject
    public TvShowFavoriteRenderer(@ForActivity Context context, TvShowsPresenter presenter, ImageLoader imageLoader) {
        super(context, presenter, imageLoader);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_discover_favorite_item, parent, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
