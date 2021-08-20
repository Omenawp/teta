package com.oelrun.teta.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oelrun.teta.repository.AuthManager
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val authManager = AuthManager.getInstance(application)

    private var _navigateToProfile = MutableLiveData<Boolean>()
    val navigateToProfile: LiveData<Boolean> = _navigateToProfile

    fun checkAuth() {
        _navigateToProfile.value = authManager.checkAuth()
    }

    fun login() {
        viewModelScope.launch {
            _navigateToProfile.value = authManager.login()
        }
    }

    fun navigationDone() {
        _navigateToProfile.value = false
    }

}