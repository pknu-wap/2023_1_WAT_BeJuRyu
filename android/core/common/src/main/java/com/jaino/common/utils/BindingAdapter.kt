package com.jaino.common.utils

import android.util.Base64
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jaino.common.extensions.toTypedKor

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

    @JvmStatic
    @BindingAdapter("app:encodedImage")
    fun loadEncodedImage(view: ImageView, encodedImage: String) {
        if (encodedImage.isNotEmpty()) {
            val image: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
            Glide.with(view.context)
                .load(image)
                .placeholder(com.jaino.designsystem.R.drawable.img)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(RequestOptions().fitCenter())
                .error(com.jaino.designsystem.R.drawable.img)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("app:typeName")
    fun setKorTypeName(view: TextView, text: String) {
        view.text = text.toTypedKor()
    }
}