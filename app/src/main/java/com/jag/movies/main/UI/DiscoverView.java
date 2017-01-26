package com.jag.movies.main.UI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jag.movies.App;
import com.jag.movies.dependencyinjection.activity.ActivityModule;
import com.jag.movies.dependencyinjection.application.ViewModule;
import com.jag.movies.dependencyinjection.qualifier.ForActivity;
import com.jag.movies.main.Model.DiscoverModel;
import com.jag.movies.main.Presenter.DiscoverPresenter;
import com.jag.movies.R;

import javax.inject.Inject;

public class DiscoverView extends AppCompatActivity implements IDiscoverView {

    @Inject
    @ForActivity
    Context context;

    @Inject
    public DiscoverPresenter discoverPresenter;

    //private final DiscoverPresenter discoverPresenter = new DiscoverPresenter(this, new DiscoverModel());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);*/
    }
}
