package com.oelrun.teta.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.network.MovieApiClient
import com.oelrun.teta.repository.TetaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RefreshMoviesWork(val appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshMoviesWork"
    }

    override suspend fun doWork(): Result {
        val repository = TetaRepositoryImpl(
            MovieApiClient.service,
            AppDatabase.getInstance(appContext.applicationContext)
        )

        return try {
            withContext(Dispatchers.IO) {
                repository.getMoviesFromNetwork(true, 1, null, null)
            }
            Result.success()
        } catch (e: HttpException) {
            Result.failure()
        }
    }
}