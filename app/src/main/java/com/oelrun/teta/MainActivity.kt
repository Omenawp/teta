package com.oelrun.teta

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.screens.login.LoginActions
import com.oelrun.teta.screens.login.LoginFragmentDirections
import com.oelrun.teta.screens.movies.*
import com.oelrun.teta.screens.profile.LogoutAction
import com.oelrun.teta.screens.profile.ProfileFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), MoviesFragmentClickListener, LoginActions, LogoutAction {

    private lateinit var bottomNavMenu: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavMenu = findViewById(R.id.bottom_navigation)
        navController = this.findNavController(R.id.nav_host_fragment)
        bottomNavMenu.setupWithNavController(navController)

        bottomNavMenu.setOnItemReselectedListener {
            if(it.itemId == R.id.moviesFragment && navController.backQueue.size > 2) {
                navController.popBackStack()
            }
        }

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPreferences = EncryptedSharedPreferences.create(
            getString(R.string.shared_preference),
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun checkAuth(): Boolean {
        val value = sharedPreferences.getString(KEY_NAME, null)
        return value != null
    }

    override fun login() {
        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val token = (1..32).map { charPool.random() }.joinToString("")

        lifecycleScope.launch {
            val res = withContext(Dispatchers.IO) {
                sharedPreferences.edit().putString(KEY_NAME, token).commit()
            }
            if(res) {
                navController.navigate(
                    LoginFragmentDirections.actionLoginFragmentToProfileFragment())
            }
        }
    }

    override fun logout() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                AppDatabase.getInstance(applicationContext).profileDao().deleteProfile()
            }

            sharedPreferences.edit().clear().apply()
            navController.navigate(
                ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
        }
    }

    override fun onBackPressed() {
        if(bottomNavMenu.selectedItemId == R.id.profileFragment) {
            bottomNavMenu.selectedItemId = R.id.moviesFragment
        } else {
            super.onBackPressed()
        }
    }

    override fun navigateToDetail(id: Int) {
        navController.navigate(MoviesFragmentDirections
            .actionMoviesFragmentToMovieDetailsFragment(id))
    }

    companion object {
        private const val KEY_NAME = "token"
    }
}