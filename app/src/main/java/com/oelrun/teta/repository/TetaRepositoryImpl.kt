package com.oelrun.teta.repository

import com.oelrun.teta.database.AppDatabase
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.database.entities.Profile
import com.oelrun.teta.database.entities.relations.*
import com.oelrun.teta.network.MovieApiService
import com.oelrun.teta.network.response.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class TetaRepositoryImpl constructor(
    private val webservice: MovieApiService,
    private val database: AppDatabase
): TetaRepository {

    override suspend fun getMovies(refresh: Boolean, page: Int, genreId: Int?): Flow<List<Movie>?>
     = flow {
        if(refresh) { deleteMovies() }

        emit(withContext(Dispatchers.IO) {
            if(genreId != null) {
                database.movieDao().getMoviesByGenre(genreId, page)
            } else {
                database.movieDao().getPopularMovies(page)
            }
        })

        emit(withContext(Dispatchers.IO) {
            val networkData = withContext(Dispatchers.IO) {
                try {
                    if(genreId != null) {
                        webservice.getMoviesByGenre(genreId = genreId, page = page)
                    } else {
                        webservice.getPopularMovies(page = page)
                    }
                } catch (e: Exception) {
                    ObjectMoviesResponse(errorMessage = "No internet connection")
                }
            }

            networkData.errorMessage?.let { throw Exception(it) }
            val data = networkData.movies?.let { movies ->
                movies.map { movie ->
                    async {
                        val cast = withContext(Dispatchers.IO) {
                            webservice.getMovieCredits(movie.id)
                        }
                        cast.cast?.let { saveCast(movie.id, it) }

                        val genresCrossRef = movie.genres.map { MovieGenreCrossRef(movie.id, it) }
                        database.movieDao().insertMovieGenreCrossRef(*genresCrossRef.toTypedArray())

                        val response = withContext(Dispatchers.IO) {
                            webservice.getMovieAgeRestriction(movie.id)
                        }
                        val age = findAgeRestriction(response)
                        movie.convertToMovieEntity(age)
                    }
                }.awaitAll()
            }
            data?.let {
                database.movieDao().insertAll(*data.toTypedArray())
            }
            data?.sortedByDescending { it.popularity }
        })
    }

    private fun findAgeRestriction(data: ObjectAgeResponse): String? {
        var age: String? = null
        val releaseRu = data.results?.filter { it.country == "RU" }
        if(!releaseRu.isNullOrEmpty()) {
            val release = releaseRu[0].data.filter { it.certification != "" }
            if (!release.isNullOrEmpty()) {
                age = release[0].certification
            }
        }

        return age
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
                webservice.getGenres()
            }
            networkData.errorMessage?.let { throw Exception(it) }
            networkData.genres?.let {
                data = it.map { it.convertToGenreEntity() }
                saveGenres(data)
            }
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

    private suspend fun saveCast(movieId: Int, cast: List<ActorResponse>) {
        val data = cast.map { it.convertToActorEntity() }
        val actorsCrossRef = cast.map { MovieActorCrossRef(movieId, it.actorId) }
        database.actorDao().insertAll(*data.toTypedArray())
        database.movieDao().insertMovieActorCrossRef(*actorsCrossRef.toTypedArray())
    }

    private suspend fun deleteMovies() {
        database.movieDao().deleteMovies()
        database.actorDao().clearActors()
    }

    private suspend fun saveGenres(genres: List<Genre>) {
        database.genreDao().insertAll(*genres.toTypedArray())
    }
}