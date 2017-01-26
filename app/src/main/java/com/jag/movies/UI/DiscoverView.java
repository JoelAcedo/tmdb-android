package com.jag.movies.UI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jag.movies.App;
import com.jag.movies.Presenter.DiscoverPresenter;
import com.jag.movies.R;
import com.jag.movies.dependencyinjector.activity.ActivityModule;
import com.jag.movies.dependencyinjector.application.DiscoverModule;
import com.jag.movies.dependencyinjector.qualifier.ForActivity;

import javax.inject.Inject;

public class DiscoverView extends AppCompatActivity implements IDiscoverView {

    //private final IDiscoverPresenter discoverPresenter = new DiscoverPresenter(this, new DiscoverModel());

    @Inject
    @ForActivity
    Context context;

    @Inject
    public DiscoverPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new DiscoverModule(this))
                .inject(this);
    }
}
