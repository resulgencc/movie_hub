package com.resulgenc.moviehub.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter("loadPoster")
fun ImageView.loadPoster(path: String?) {
    if (path?.isNotEmpty() == true) {
        Glide
            .with(this)
            .load(path)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
    }
}