package com.oelrun.teta.screens.movies

import android.app.Application
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.database.entities.relations.MovieActorCrossRef
import com.oelrun.teta.database.entities.relations.MovieFullInfo
import com.oelrun.teta.database.entities.relations.MovieGenreCrossRef
import com.oelrun.teta.network.MovieApi
import kotlinx.coroutines.Dispatchers
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

    private val database = AppDatabase.getInstance(application)

    init {
        loadGenres()
        loadMovies(false)
    }

    fun loadMovies(refresh: Boolean) {
        _isRefreshing.value = true
        _firstItemMovie = -1

        viewModelScope.launch {
            try {
                var data = withContext(Dispatchers.IO) {
                    database.movieDao().getAllMovies()
                }
                if(data.isEmpty()) {
                    val networkData = withContext(Dispatchers.IO) {
                        MovieApi.repository.getMovies(refresh)
                    }
                    saveToDatabase(networkData)
                    data = networkData.map { it.movie }
                }
                _moviesData.value = data
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isRefreshing.value = false
        }
    }

    fun updateMovies(refresh: Boolean) {
        viewModelScope.launch {
            database.movieDao().deleteMovies()
            database.actorDao().clearActors()
            loadMovies(refresh)
        }
    }

    private fun saveToDatabase(networkData: List<MovieFullInfo>) {
        viewModelScope.launch(Dispatchers.IO) {
            networkData.forEach { full ->
                val id = full.movie.movieId
                database.movieDao().insertAll(full.movie)

                full.actors?.let { actors ->
                    database.actorDao().insertAll(*actors.toTypedArray())
                    val actorsCrossRef = actors.map { MovieActorCrossRef(id, it.actorId) }
                    database.movieDao().insertMovieActorCrossRef(*actorsCrossRef.toTypedArray())
                }

                full.genres.let { list ->
                    val genresCrossRef = list.map { MovieGenreCrossRef(id, it.genreId) }
                    database.movieDao().insertMovieGenreCrossRef(*genresCrossRef.toTypedArray())
                }
            }
        }
    }

    private fun loadGenres() {
        viewModelScope.launch {
            try {
                var data = withContext(Dispatchers.IO) {
                    database.genreDao().getAllGenres()
                }
                if(data.isEmpty()) {
                    val networkData = withContext(Dispatchers.IO) {
                        MovieApi.repository.getGenres()
                    }
                    database.genreDao().insertAll(*networkData.toTypedArray())
                    data = networkData
                }
                _genresData.value = data

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