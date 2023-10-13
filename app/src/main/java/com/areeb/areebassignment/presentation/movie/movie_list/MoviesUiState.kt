package com.areeb.areebassignment.presentation.movie.movie_list

import androidx.paging.PagingData
import com.areeb.areebassignment.domain.model.Movie

data class MoviesUiState(
    val movies: PagingData<Movie>? = null,
)
