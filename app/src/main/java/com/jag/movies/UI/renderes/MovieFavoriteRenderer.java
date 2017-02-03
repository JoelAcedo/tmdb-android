package com.jag.movies.UI.renderes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jag.movies.Presenter.DiscoverPresenter;
import com.jag.movies.R;
import com.jag.movies.Utils.ImageLoader;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by inlab on 03/02/2017.
 */

public class MovieFavoriteRenderer extends MovieRenderer {

    @Inject
    public MovieFavoriteRenderer(@ForActivity Context context, DiscoverPresenter discoverPresenter, ImageLoader imageLoader) {
        super(context, discoverPresenter, imageLoader);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_discover_favorite_item, parent, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
