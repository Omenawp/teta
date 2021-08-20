package com.oelrun.teta.screens.movies

import android.app.Application
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.oelrun.teta.R
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.network.MovieApiClient
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

    val res = application.resources

    private var _firstItemMovie = -1
    val firstItemMovie
        get() = _firstItemMovie

    private val repository = TetaRepositoryImpl(
        MovieApiClient.service,
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
                val data = repository.getMovies(refresh)
                if(data.isEmpty()) {
                    _errorMessage.value = res.getString(R.string.movie_list_error_message_empty)
                }
                _moviesData.value = data
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _moviesData.value = emptyList()
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

    fun savePosition(lm: GridLayoutManager) {
        var pos = lm.findFirstCompletelyVisibleItemPosition()
        if(pos == -1) {
            pos = lm.findFirstVisibleItemPosition()
        }
        _firstItemMovie = pos
    }
}