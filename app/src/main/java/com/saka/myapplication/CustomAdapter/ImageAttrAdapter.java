package com.saka.myapplication.CustomAdapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by saka on 2017/1/2.
 */

public class ImageAttrAdapter {
    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }
}
