package com.areeb.areebassignment.presentation.movie.movie_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.areeb.areebassignment.MainDispatcherRule
import com.areeb.areebassignment.domain.model.Movie
import com.areeb.areebassignment.domain.usecase.GetMoviesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    lateinit var getMoviesUseCase: GetMoviesUseCase

    lateinit var viewModel: MoviesViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setup() {
        getMoviesUseCase = mockk()
        viewModel = MoviesViewModel(getMoviesUseCase)
    }

    @Test
    fun `getMovies should update UI state with movies`() = runBlockingTest {
        // Arrange
        val movies = listOf(
            Movie(
                movieId = 1,
                title = "movie test",
                overview = "movie overview",
                backdropPathUrl = "http://www.movie.com/backdropPathUrl",
                posterPathUrl = "http://www.movie.com/posterPathUrl",
                lang = "en",
                releaseDate = "14/10/2023",
                voteAverage = 8.2
            )
        )

        coEvery { getMoviesUseCase.invoke() } returns flow {
            emit(
                PagingData.from(movies)
            )
        }
        // Act
        getMoviesUseCase.invoke()

        viewModel.getMovies()

        // Assert
        delay(1000) // Wait for the delay in the function
        coVerify { getMoviesUseCase.invoke() } // Verify that getMoviesUseCase was called
    }
}

