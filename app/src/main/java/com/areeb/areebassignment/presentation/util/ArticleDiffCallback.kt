package com.areeb.areebassignment.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.areeb.areebassignment.domain.model.Movie

object ArticleDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.posterPathUrl == newItem.posterPathUrl
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}