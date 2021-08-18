package com.oelrun.teta.repository

import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.database.entities.relations.MovieFullInfo
import com.oelrun.teta.database.entities.relations.ProfileWithGenres

interface TetaRepository {
    suspend fun getMovies(refresh: Boolean): List<Movie>
    suspend fun getMovieDetails(id: Int): MovieFullInfo
    suspend fun getGenres(): List<Genre>
    suspend fun getProfile(): ProfileWithGenres?
    suspend fun deleteProfile()
}