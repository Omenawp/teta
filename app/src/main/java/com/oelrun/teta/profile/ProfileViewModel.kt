package com.oelrun.teta.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.data.genre.GenresDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel: ViewModel() {
    private val _favGenres = MutableStateFlow<List<GenreDto>?>(null)
    val favGenres: StateFlow<List<GenreDto>?> = _favGenres

    private var _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadFavGenres()
    }

    private fun loadFavGenres() {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    GenresDataSource().getGenres()
                }
                if (data.isNotEmpty()) {
                    _favGenres.value = data
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }
}