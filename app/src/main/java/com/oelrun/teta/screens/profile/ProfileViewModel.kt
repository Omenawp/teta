package com.oelrun.teta.screens.profile

import android.app.Application
import androidx.lifecycle.*
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Profile
import com.oelrun.teta.database.entities.relations.ProfileGenreCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    private val _userProfile = MutableLiveData<Profile>()
    val userProfile: LiveData<Profile> = _userProfile

    private val _favGenres = MutableLiveData<List<Genre>?>()
    val favGenres: LiveData<List<Genre>?> = _favGenres

    private var _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val database = AppDatabase.getInstance(application)

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {
                val userData = database.profileDao().getProfileWithGenres()
                if(userData == null) {
                    val profile = Profile(
                        1,
                        "Шарик",
                        "password1",
                        null,
                        "sharik@homyak.net",
                        null
                    )
                    val favGenres = arrayOf(1, 11, 7, 3, 17, 5)
                    database.profileDao().insert(profile)
                    val crossRef = favGenres.map { ProfileGenreCrossRef(1, it) }
                    database.profileDao().insertProfileGenreCrossRef(*crossRef.toTypedArray())

                    _userProfile.value = profile
                } else {
                    _userProfile.value = userData.profile
                }


                val genres = withContext(Dispatchers.IO) {
                    database.profileDao().getProfileWithGenres()?.genres
                }
                _favGenres.value = genres

            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }
}