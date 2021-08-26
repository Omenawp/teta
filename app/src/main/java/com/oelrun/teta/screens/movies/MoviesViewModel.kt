package com.oelrun.teta.screens.movies

import android.app.Application
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.network.MovieApiClient
import com.oelrun.teta.repository.TetaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        MovieApiClient.service,
        AppDatabase.getInstance(application.applicationContext)
    )

    private var page = 1
    private var genreId: Int? = null

    init {
        loadGenres()
        loadMovies(false)
    }

    fun loadMovies(refresh: Boolean) {
        _isRefreshing.value = true
        _firstItemMovie = -1
        if(refresh) {
            page = 1
        }

        viewModelScope.launch {
            try {
                val current = page
                withContext(Dispatchers.IO) {
                    repository.getMovies(refresh, page, genreId)
                }.onEach { newData ->
                    if(newData != null) {
                        val size = (current - 1) * 20
                        val list = _moviesData.value?.take(size)?.toMutableList()
                        _moviesData.value = list?.plus(newData) ?: newData
                    }
                }.collect()
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
        page = 1
        _firstItemMovie = 0
        _moviesData.value = emptyList()

        _genresData.value?.forEach {
            if(it.genreId == item.genreId) {
                it.selected = !it.selected
                genreId = if(it.selected) it.genreId else null
            } else {
                if(it.selected) {
                    it.selected = false
                }
            }
        }

        loadMovies(false)
    }

    fun savePosition(lm: GridLayoutManager) {
        var pos = lm.findFirstCompletelyVisibleItemPosition()
        if(pos == -1) {
            pos = lm.findFirstVisibleItemPosition()
        }
        _firstItemMovie = pos
    }

    fun loadNext() {
        page++
        loadMovies(false)
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }
}