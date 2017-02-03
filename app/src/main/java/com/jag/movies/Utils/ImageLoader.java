package com.jag.movies.Utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

/**
 * Created by joela on 30/01/2017.
 */

public interface ImageLoader {

    void bindImage (String imagePath, ImageView imageView);
    void bindImage (String imagePath, ImageView imageView, ProgressBar progressBar);
    void bindImage (String imagePath, ImageView imageView, int placeholder);

    Bitmap getBitmap(ImageView movieCover);
}
