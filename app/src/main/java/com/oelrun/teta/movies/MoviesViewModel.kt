package com.oelrun.teta.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.data.genre.GenresDataSource
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

    private val _genresData = MutableStateFlow<List<GenreDto>>(emptyList())
    val genresData: StateFlow<List<GenreDto>> = _genresData

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private var _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadGenres()
        loadMovies(false)
    }

    fun loadMovies(refresh: Boolean) {
        _isRefreshing.value = true

        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    Thread.sleep(2000)
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

    private fun loadGenres() {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    Thread.sleep(2000)
                    GenresDataSource().getGenres()
                }
                if (data.isNotEmpty()) {
                    _genresData.value = data
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun genreChangeSelection(item: GenreDto) {
        val i = _genresData.value.indexOf(item)
        _genresData.value[i].selected = !item.selected
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }
}