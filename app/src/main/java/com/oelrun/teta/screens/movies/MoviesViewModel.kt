package com.oelrun.teta.screens.movies

import android.app.Application
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.network.MovieApi
import com.oelrun.teta.repository.TetaRepositoryImpl
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application): AndroidViewModel(application) {
    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> = _moviesData

    private val _genresData = MutableLiveData<List<Genre>>()
    val genresData: LiveData<List<Genre>> = _genresData

    private var _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private var _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private var _firstItemMovie = -1
    val firstItemMovie
        get() = _firstItemMovie

    private val repository = TetaRepositoryImpl(
        MovieApi.webservice,
        AppDatabase.getInstance(application.applicationContext)
    )

    init {
        loadGenres()
        loadMovies(false)
    }

    fun loadMovies(refresh: Boolean) {
        _isRefreshing.value = true
        _firstItemMovie = -1

        viewModelScope.launch {
            try {
                _moviesData.value = repository.getMovies(refresh)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isRefreshing.value = false
        }
    }

    private fun loadGenres() {
        viewModelScope.launch {
            try {
                _genresData.value = repository.getGenres()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun genreChangeSelection(item: Genre) {
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