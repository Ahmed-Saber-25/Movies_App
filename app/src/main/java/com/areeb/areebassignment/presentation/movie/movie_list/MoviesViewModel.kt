package com.areeb.areebassignment.presentation.movie.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.areeb.areebassignment.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
@Inject
constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    init {
        onEvent(MoviesEvents.GetMovies)
    }

    private fun onEvent(event: MoviesEvents) {
        when (event) {
            is MoviesEvents.GetMovies -> {
                getMovies()
            }
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            delay(1000)
            getMoviesUseCase()
                .cachedIn(viewModelScope)
                .collect { movies ->
                    _uiState.update { it.copy(movies = movies) }
                }
        }
    }

}