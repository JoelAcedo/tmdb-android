package com.jag.movies.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.jag.movies.dependencyinjector.qualifier.ForApp;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by joela on 30/01/2017.
 */

public class PicassoLoader implements ImageLoader {

    private final Context context;

    @Inject
    public PicassoLoader(@ForApp Context context) {
        this.context = context;
    }

    @Override
    public void bindImage(String imagePath, ImageView imageView) {
        Picasso.with(context)
                .load(imagePath)
                .noFade()
                .centerInside()
                .fit()
                .into(imageView);
    }

    @Override
    public void bindImage(String imagePath, ImageView imageView, int placeholder) {
        Picasso.with(context)
                .load(imagePath)
                .noFade()
                .centerInside()
                .fit()
                .placeholder(placeholder)
                .into(imageView);
    }

    @Override
    public Bitmap getBitmap(ImageView movieCover) {
        return ((BitmapDrawable) movieCover.getDrawable()).getBitmap();
    }
}
