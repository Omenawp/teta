package com.oelrun.teta.screens.profile

import androidx.lifecycle.*
import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel: ViewModel() {

    private val _favGenres = MutableLiveData<List<GenreDto>>()
    val favGenres: LiveData<List<GenreDto>> = _favGenres

    private var _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        loadFavGenres()
    }

    private fun loadFavGenres() {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    MovieApi.repository.getGenres()
                }
                _favGenres.value = data
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }
}