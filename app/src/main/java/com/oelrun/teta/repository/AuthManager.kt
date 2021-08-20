package com.oelrun.teta.repository

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.oelrun.teta.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthManager(context: Context) {
    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        context.getString(R.string.shared_preference),
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun checkAuth(): Boolean {
        val value = sharedPreferences.getString(KEY_NAME, null)
        return value != null
    }

    suspend fun login(): Boolean {
        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val token = (1..32).map { charPool.random() }.joinToString("")

        return withContext(Dispatchers.IO) {
            sharedPreferences.edit().putString(KEY_NAME, token).commit()
        }
    }

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private var INSTANCE: AuthManager? = null
        private const val KEY_NAME = "token"

        fun getInstance(context: Context): AuthManager {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = AuthManager(context.applicationContext)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
