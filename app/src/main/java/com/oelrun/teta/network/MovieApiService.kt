package com.oelrun.teta.network

import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.relations.MovieFullInfo

interface MovieApiService {
    suspend fun getMovies(refresh: Boolean): List<MovieFullInfo>
    suspend fun getMovieDetails(id: Int): MovieFullInfo?
    suspend fun getGenres(): List<Genre>
}