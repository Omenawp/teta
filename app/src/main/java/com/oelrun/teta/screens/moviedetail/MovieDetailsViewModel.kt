package com.oelrun.teta.screens.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oelrun.teta.data.movie.MovieDto
import com.oelrun.teta.data.movie.MoviesDataSource
import com.oelrun.teta.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel: ViewModel() {
    private val _movieDetails = MutableStateFlow<MovieDto?>(null)
    val movieDetails: StateFlow<MovieDto?> = _movieDetails

    private var _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadDetails(id: Int) {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    MovieApi.repository.getMovieDetails(id)
                }
                _movieDetails.value = data
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}