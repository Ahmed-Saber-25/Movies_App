package com.stc.stcassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.areeb.areebassignment.data.repository.MoviePagingSource
import com.areeb.areebassignment.data.local.MovieDao
import com.areeb.areebassignment.data.network.MovieApi
import com.areeb.areebassignment.data.network.dto.movie.MovieDtoMapper
import com.areeb.areebassignment.domain.model.Movie
import com.areeb.areebassignment.domain.repository.MoviesRepository
import com.areeb.areebassignment.domain.util.Constants.Companion.PAGINATION_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl
@Inject constructor(
    private val api: MovieApi,
    private val movieDtoMapper: MovieDtoMapper,
    private val localSource: MovieDao,
) : MoviesRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(PAGINATION_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(api, movieDtoMapper, localSource) }).flow
    }

}