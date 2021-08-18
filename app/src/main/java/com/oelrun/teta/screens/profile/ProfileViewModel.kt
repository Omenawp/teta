package com.oelrun.teta.screens.profile

import android.app.Application
import androidx.lifecycle.*
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.relations.ProfileWithGenres
import com.oelrun.teta.network.MovieApi
import com.oelrun.teta.repository.AuthManager
import com.oelrun.teta.repository.TetaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    private val _userProfile = MutableLiveData<ProfileWithGenres>()
    val userProfile: LiveData<ProfileWithGenres> = _userProfile

    private var _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val repository = TetaRepositoryImpl(
        MovieApi.webservice,
        AppDatabase.getInstance(application.applicationContext)
    )

    private val authManager = AuthManager.getInstance(application)

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {
                _userProfile.value = repository.getProfile()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            authManager.logout()
            repository.deleteProfile()
        }
    }
}