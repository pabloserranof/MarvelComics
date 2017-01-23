package com.pabloserrano.marvelcomics.network;

import android.content.Context;
import android.widget.ImageView;

import com.pabloserrano.marvelcomics.data.model.Thumbnail;
import com.squareup.picasso.Picasso;

public class ImageLoader {

    public static void loadThumbnail(Context context, Thumbnail thumbnail, ImageView imageView) {
        try {
            Picasso.with(context).
                    load(new StringBuilder(thumbnail.getPath()).append(".").append(thumbnail.getExtension()).toString())
                    .fit().centerCrop().into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
