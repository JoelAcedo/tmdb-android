package com.jag.movies.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
    public void bindImage(String imagePath, ImageView imageView, final ProgressBar progressBar) {
        Glide.with(context)
                .load(imagePath)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).dontAnimate().fitCenter().into(imageView);

//                .dontAnimate()
//                .fitCenter()
//                .into(imageView);
    }

    @Override
    public void bindImage(String imagePath, ImageView imageView, int placeholder) {
        Glide.with(context)
                .load(imagePath)
                .dontAnimate()
                .fitCenter()
                .placeholder(placeholder)
                .into(imageView);
    }

    @Override
    public Bitmap getBitmap(ImageView movieCover) {
        return ((GlideBitmapDrawable) movieCover.getDrawable()).getBitmap();
    }
}
