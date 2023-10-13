package com.areeb.areebassignment.domain.usecase

import androidx.paging.PagingData
import com.areeb.areebassignment.domain.model.Movie
import com.areeb.areebassignment.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase
@Inject constructor(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(): Flow<PagingData<Movie>> = moviesRepository.getMovies()
}