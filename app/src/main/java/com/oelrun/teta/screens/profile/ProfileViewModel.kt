package com.oelrun.teta.screens.profile

import android.app.Application
import androidx.lifecycle.*
import com.oelrun.teta.R
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.Profile
import com.oelrun.teta.database.entities.relations.ProfileWithGenres
import com.oelrun.teta.network.MovieApiClient
import com.oelrun.teta.repository.AuthManager
import com.oelrun.teta.repository.TetaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    private val _userProfile = MutableLiveData<ProfileWithGenres>()
    val userProfile: LiveData<ProfileWithGenres> = _userProfile

    private var _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private var _editErrorMessage = MutableLiveData<String?>()
    val editErrorMessage: LiveData<String?> = _editErrorMessage

    private val repository = TetaRepositoryImpl(
        MovieApiClient.service,
        AppDatabase.getInstance(application.applicationContext)
    )

    private val authManager = AuthManager.getInstance(application)
    private val res = application.resources

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

    fun changeName(newName: String): String? {
        if(newName.length in 3..14) {
            _userProfile.value?.profile?.let {
                it.userName = newName
                changeProfile(it)
                return null
            }
        }

        _editErrorMessage.value = res.getString(R.string.profile_invalid_name)
        return _userProfile.value?.profile?.userName
    }

    fun changePhone(phone: String?): String? {
        if(phone?.length == 10 || phone == null) {
            _userProfile.value?.profile?.let {
                it.phoneNumber = phone
                changeProfile(it)
                return null
            }
        }

        _editErrorMessage.value = res.getString(R.string.profile_invalid_phone)
        return _userProfile.value?.profile?.phoneNumber ?: ""
    }

    fun editErrorMessageShown() {
        _editErrorMessage.value = null
    }


    private fun changeProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProfile(profile)
        }
    }
}