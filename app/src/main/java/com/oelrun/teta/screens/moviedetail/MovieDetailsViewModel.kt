package com.oelrun.teta.screens.moviedetail

import android.app.Application
import androidx.lifecycle.*
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.relations.MovieFullInfo
import com.oelrun.teta.network.MovieApi
import com.oelrun.teta.repository.TetaRepositoryImpl
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application): AndroidViewModel(application) {
    private val _movieDetails = MutableLiveData<MovieFullInfo?>()
    val movieDetails: LiveData<MovieFullInfo?> = _movieDetails

    private var _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val repository = TetaRepositoryImpl(
        MovieApi.webservice,
        AppDatabase.getInstance(application.applicationContext)
    )

    fun loadDetails(id: Int) {
        viewModelScope.launch {
            try {
                _movieDetails.value = repository.getMovieDetails(id)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}