package com.oelrun.teta.screens.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.data.movie.MovieDto
import com.oelrun.teta.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel: ViewModel() {
    private val _moviesData = MutableLiveData<List<MovieDto>>()
    val moviesData: LiveData<List<MovieDto>> = _moviesData

    private val _genresData = MutableLiveData<List<GenreDto>>()
    val genresData: LiveData<List<GenreDto>> = _genresData

    private var _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private var _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private var _firstItemMovie = -1
    val firstItemMovie
        get() = _firstItemMovie

    init {
        loadGenres()
        loadMovies(false)
    }

    fun loadMovies(refresh: Boolean) {
        _isRefreshing.value = true
        _firstItemMovie = -1

        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    MovieApi.repository.getMovies(refresh)
                }
                _moviesData.value = data
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
                    MovieApi.repository.getGenres()
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
        _genresData.value?.let { genres ->
            val i = genres.indexOf(item)
            genres[i].selected = !item.selected
        }
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }

    fun savePosition(lm: GridLayoutManager) {
        var pos = lm.findFirstCompletelyVisibleItemPosition()
        if(pos == -1) {
            pos = lm.findFirstVisibleItemPosition()
        }
        _firstItemMovie = pos
    }
}