package com.areeb.areebassignment.presentation.movie.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.areeb.areebassignment.databinding.MovieListItemBinding
import com.areeb.areebassignment.domain.model.Movie
import com.areeb.areebassignment.presentation.util.ArticleDiffCallback
import com.areeb.areebassignment.presentation.util.loadImage

class MoviesListAdapter(
    private val clicked: (Movie) -> Unit,
) : PagingDataAdapter<Movie, MoviesListAdapter.ArticleViewHolder>(ArticleDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArticleViewHolder {
        return ArticleViewHolder(
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    inner class ArticleViewHolder(
        private val binding: MovieListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie?) {
            binding.movie = item
            binding.root.setOnClickListener {
                item?.let { movieItem -> clicked.invoke(movieItem) }
            }
        }
    }
}


