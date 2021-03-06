package com.carter.javaAndroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.carter.javaAndroid.R;
import com.carter.javaAndroid.di.module.GlideApp;

public class GlideImageLoader {
    /**
     *
     * @param context context
     * @param url image url
     * @param iv imageView
     */
    public static void load(Context context, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.bg_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(iv);

    }
}
