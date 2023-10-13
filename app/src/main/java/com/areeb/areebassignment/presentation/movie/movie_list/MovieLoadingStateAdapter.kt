package com.areeb.areebassignment.presentation.movie.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.areeb.areebassignment.R
import com.areeb.areebassignment.databinding.MovieLoadingStateItemBinding

class MovieLoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MovieLoadingStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, loadState: LoadState,
    ) = LoadStateViewHolder(parent)


    override fun onBindViewHolder(
        holder: LoadStateViewHolder, loadState: LoadState,
    ) = holder.bind(loadState)

    inner class LoadStateViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.movie_loading_state_item, parent, false)) {

        private val binding = MovieLoadingStateItemBinding.bind(itemView)


        fun bind(loadState: LoadState) {

            if (loadState is LoadState.Error) {
                binding.txtError.text = binding.root.context.getString(R.string.check_network_connection)
            }
            binding.btnRetry.also {
                it.setOnClickListener { retry() }
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.txtError.isVisible = loadState is LoadState.Error
        }
    }
}