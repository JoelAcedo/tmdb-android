package com.jag.movies.UI.Discover.fragments;

import android.widget.ImageView;

import com.jag.movies.Models.MovieViewModel;

import java.util.List;

/**
 * Created by joela on 12/02/2017.
 */

public interface FragmentDiscoverView<T> {

    void showData(List<T> viewModelData);
    void addData(List<T> viewModelData);
    void startDetailActivity(int itemId, ImageView cover);
    void updateItemFavoritedState(T item, int position);
}
