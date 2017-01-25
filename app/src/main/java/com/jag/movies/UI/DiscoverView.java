package com.jag.movies.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jag.movies.Model.DiscoverModel;
import com.jag.movies.Presenter.DiscoverPresenter;
import com.jag.movies.Presenter.IDiscoverPresenter;
import com.jag.movies.R;

public class DiscoverView extends AppCompatActivity implements IDiscoverView {

    private final IDiscoverPresenter discoverPresenter = new DiscoverPresenter(this, new DiscoverModel());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
