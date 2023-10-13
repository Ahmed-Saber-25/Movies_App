package com.areeb.areebassignment.domain.repository

import androidx.paging.PagingData
import com.areeb.areebassignment.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(): Flow<PagingData<Movie>>
}