package com.jaino.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageView, src: String?) {
        if (src != null) {
            Glide.with(view.context)
                .load(src)
                .placeholder(com.jaino.designsystem.R.drawable.img)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(RequestOptions().fitCenter())
                .error(com.jaino.designsystem.R.drawable.img)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("app:drawableId")
    fun loadDrawable(view: ImageView, drawableId: Int) {
        if (drawableId != 0) {
            Glide.with(view.context)
                .load(drawableId)
                .placeholder(com.jaino.designsystem.R.drawable.img)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(RequestOptions().fitCenter())
                .error(com.jaino.designsystem.R.drawable.img)
                .into(view)
        }
    }
}