package com.jag.movies.Utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

/**
 * Created by joela on 30/01/2017.
 */

public interface ImageLoader {

    void bindImage (String imagePath, ImageView imageView);

    Bitmap getBitmap(ImageView movieCover);
}
