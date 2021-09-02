package com.oelrun.teta.repository

import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.database.entities.Profile
import com.oelrun.teta.database.entities.relations.MovieFullInfo
import com.oelrun.teta.database.entities.relations.ProfileWithGenres
import kotlinx.coroutines.flow.Flow

interface TetaRepository {
    suspend fun getMovies(
        refresh: Boolean,
        page: Int,
        genreId: Int? = null,
        search: String? = null
    ): Flow<List<Movie>?>

    suspend fun getMovieDetails(id: Int): MovieFullInfo
    suspend fun getGenres(): List<Genre>
    suspend fun getProfile(): ProfileWithGenres?
    suspend fun deleteProfile()
    suspend fun updateProfile(newProfile: Profile)
}