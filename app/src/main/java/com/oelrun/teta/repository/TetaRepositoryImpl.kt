package com.oelrun.teta.repository

import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.database.entities.Profile
import com.oelrun.teta.database.entities.relations.*
import com.oelrun.teta.network.MovieApi
import com.oelrun.teta.network.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TetaRepositoryImpl constructor(
    private val webservice: MovieApiService,
    private val database: AppDatabase
): TetaRepository {

    override suspend fun getMovies(refresh: Boolean): List<Movie> {
        if(refresh) { deleteMovies() }

        var data = withContext(Dispatchers.IO) {
            database.movieDao().getAllMovies()
        }
        if(data.isEmpty()) {
            val networkData = withContext(Dispatchers.IO) {
                webservice.getMovies(refresh)
            }
            saveMovies(networkData)
            data = networkData.map { it.movie }
        }

        return data
    }

    override suspend fun getMovieDetails(id: Int): MovieFullInfo = withContext(Dispatchers.IO) {
        database.movieDao().getMovieWithFullInfo(id)[0]
    }

    override suspend fun getGenres(): List<Genre> {
        var data = withContext(Dispatchers.IO) {
            database.genreDao().getAllGenres()
        }
        if(data.isEmpty()) {
            val networkData = withContext(Dispatchers.IO) {
                MovieApi.webservice.getGenres()
            }
            saveGenres(networkData)
            data = networkData
        }

        return data
    }

    override suspend fun getProfile(): ProfileWithGenres? {
        var userData = database.profileDao().getProfileWithGenres()
        if (userData == null) {
            val profile = Profile(
                1,
                "Шарик",
                "password1",
                null,
                "sharik@homyak.net",
                null
            )
            val favGenres = arrayOf(12, 14, 17, 10, 16, 18)
            val crossRef = favGenres.map { ProfileGenreCrossRef(1, it) }
            database.profileDao().insert(profile)
            database.profileDao().insertProfileGenreCrossRef(*crossRef.toTypedArray())
            userData = database.profileDao().getProfileWithGenres()
        }

        return userData
    }

    override suspend fun updateProfile(newProfile: Profile) {
        database.profileDao().insert(newProfile)
    }

    override suspend fun deleteProfile() {
        database.profileDao().deleteProfile()
    }

    private suspend fun saveMovies(networkData: List<MovieFullInfo>) {
        networkData.forEach { full ->
            val id = full.movie.movieId
            database.movieDao().insertAll(full.movie)

            full.actors?.let { actors ->
                database.actorDao().insertAll(*actors.toTypedArray())
                val actorsCrossRef = actors.map { MovieActorCrossRef(id, it.actorId) }
                database.movieDao().insertMovieActorCrossRef(*actorsCrossRef.toTypedArray())
            }

            full.genres.let { list ->
                val genresCrossRef = list.map { MovieGenreCrossRef(id, it.genreId) }
                database.movieDao().insertMovieGenreCrossRef(*genresCrossRef.toTypedArray())
            }
        }
    }

    private suspend fun deleteMovies() {
        database.movieDao().deleteMovies()
        database.actorDao().clearActors()
    }

    private suspend fun saveGenres(genres: List<Genre>) {
        database.genreDao().insertAll(*genres.toTypedArray())
    }
}