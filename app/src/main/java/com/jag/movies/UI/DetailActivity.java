package com.jag.movies.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.jag.movies.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joela on 28/01/2017.
 */

public class DetailActivity extends AppCompatActivity implements IDetailView {

    @BindView(R.id.collapsingToolbar_detail)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;

    @BindView(R.id.imageToolbar_detail)
    ImageView toolbarImage;

    @BindView(R.id.scroll_detail)
    NestedScrollView nestedScrollView;

    @Inject
    Context context;

    public static Intent getLauncherIntent() {

        Intent intent = new Intent(context, DetailCompanyActivity.class);
        intent.putExtra(DetailCompanyActivity.ID_COMPANY_INTENT, comapnyID);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
    }
}
