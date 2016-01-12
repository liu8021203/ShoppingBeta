package com.nan.shopping.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nan.shopping.R;

/**
 * Glide工具类
 * Created by liu on 15/6/29.
 */
public class UtilGlide {

    /**
     * 使用Glide加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImg(Context context, String url, ImageView imageView)
    {
        Glide.with(context).load(url).centerCrop().placeholder(R.mipmap.ic_launcher).crossFade().into(imageView);
    }

    public static void loadImg(Context context, String url, int resId, ImageView imageView)
    {
        Glide.with(context).load(url).centerCrop().placeholder(resId).crossFade().into(imageView);
    }
}
