package com.oelrun.teta.screens.moviedetail

import android.app.Application
import androidx.lifecycle.*
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.relations.MovieFullInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(application: Application): AndroidViewModel(application) {
    private val _movieDetails = MutableLiveData<MovieFullInfo?>()
    val movieDetails: LiveData<MovieFullInfo?> = _movieDetails

    private var _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val database = AppDatabase.getInstance(application)

    fun loadDetails(id: Int) {
        viewModelScope.launch {
            try {
                /*val data = withContext(Dispatchers.IO) {
                    MovieApi.repository.getMovieDetails(id)
                }*/
                val data = withContext(Dispatchers.IO) {
                    database.movieDao().getMovieWithFullInfo(id)
                }
                _movieDetails.value = data[0]
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}