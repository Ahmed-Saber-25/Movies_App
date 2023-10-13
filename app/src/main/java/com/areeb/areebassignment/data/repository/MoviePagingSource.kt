package com.areeb.areebassignment.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.areeb.areebassignment.data.local.MovieDao
import com.areeb.areebassignment.data.network.MovieApi
import com.areeb.areebassignment.data.network.dto.movie.MovieDtoMapper
import com.areeb.areebassignment.domain.model.Movie
import com.areeb.areebassignment.domain.util.Constants.Companion.API_KEY
import com.areeb.areebassignment.domain.util.Constants.Companion.STARTING_PAGE_INDEX
import java.net.UnknownHostException
import javax.inject.Inject

class MoviePagingSource
@Inject constructor(
    private val api: MovieApi,
    private val movieDtoMapper: MovieDtoMapper,
    private val localSource: MovieDao,
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val position = params.key ?: 1
            val response = api.getMovies(API_KEY, page)
            val movies = movieDtoMapper.toDomainList(response.body()?.movies ?: listOf())
            localSource.upsert(movies)
            LoadResult.Page(
                data = movies,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.body()!!.totalPages == position) null else position + 1
            )
        } catch (e: Exception) {
            val cachedMovies = localSource.getAllMovies()
            if (e is UnknownHostException && page == 1 && cachedMovies.isNotEmpty()) {
                return LoadResult.Page(
                    data = cachedMovies,
                    prevKey = null,
                    nextKey = null
                )
            } else {
                return LoadResult.Error(e)
            }
        }
    }
}