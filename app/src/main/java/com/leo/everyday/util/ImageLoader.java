package com.leo.everyday.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leo.everyday.InitApp;
import com.leo.everyday.R;

/**
 * 作者：Leo on 2018/1/26 15:46
 * <p>
 * 描述：
 */

public class ImageLoader {

    public static void loadImage(ImageView imageView, String url, String flag) {
        if ("true".equals(flag))
            Glide.with(InitApp.AppContext)
                    .load(url)
                    .asGif()
                    .placeholder(R.mipmap.default_icon)
                    .error(R.mipmap.default_icon)
                    .into(imageView);
        else
            Glide.with(InitApp.AppContext)
                    .load(url)
                    .asBitmap()
                    .placeholder(R.mipmap.default_icon)
                    .error(R.mipmap.default_icon)
                    .into(imageView);
    }


    public static void loadImage(ImageView imageView, String url) {
        Glide.with(InitApp.AppContext)
                .load(url)
                .asBitmap()
                .placeholder(R.mipmap.default_icon)
                .error(R.mipmap.default_icon)
                .into(imageView);
    }

}
