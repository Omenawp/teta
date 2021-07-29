package com.oelrun.teta.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oelrun.teta.data.movie.MovieDto
import com.oelrun.teta.data.movie.MoviesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel: ViewModel() {
    private val _moviesData = MutableStateFlow<List<MovieDto>>(emptyList())
    val moviesData: StateFlow<List<MovieDto>> = _moviesData

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private var _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadMovies(false)
    }

    fun loadMovies(refresh: Boolean) {
        _isRefreshing.value = true

        viewModelScope.launch {
            try {
                Thread.sleep(2000)
                val data = withContext(Dispatchers.IO) {
                    MoviesDataSource().getMovies(refresh)
                }
                if (data.isNotEmpty()) {
                    _moviesData.value = data
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isRefreshing.value = false
        }
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }
}