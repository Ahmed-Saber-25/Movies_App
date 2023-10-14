package com.areeb.areebassignment.presentation.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.areeb.areebassignment.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@SuppressLint("CutPasteId")
fun Activity.showRetry(
    visibility: Boolean,
    message: String?,
) {
    findViewById<TextView>(R.id.tv_error).isVisible = visibility
    findViewById<Button>(R.id.btn_retry).isVisible = visibility
    findViewById<TextView>(R.id.tv_error).text = message
}
fun ImageView.loadImage(context: Context,imageUrl:String?) {
    Glide.with(context)
        .applyDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.movie_img_placeholder)
            .diskCacheStrategy(
                DiskCacheStrategy.ALL))
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
@BindingAdapter("loadImage")
fun loadImage(view: ImageView,imageUrl: String?){
    view.loadImage(view.context,imageUrl)
}