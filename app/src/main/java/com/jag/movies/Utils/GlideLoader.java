package com.jag.movies.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.jag.movies.dependencyinjector.qualifier.ForApp;

import javax.inject.Inject;

/**
 * Created by joela on 30/01/2017.
 */

public class GlideLoader implements ImageLoader {

    private final Context context;

    @Inject
    public GlideLoader(@ForApp Context context) {
        this.context = context;
    }

    @Override
    public void bindImage(String imagePath, ImageView imageView) {
        Glide.with(context)
                .load(imagePath)
                .dontAnimate()
                .fitCenter()
                .into(imageView);
    }

    @Override
    public Bitmap getBitmap(ImageView movieCover) {
        return ((GlideBitmapDrawable) movieCover.getDrawable()).getBitmap();
    }
}
