package com.oelrun.teta.network

import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.data.movie.MovieDto

interface MovieApiService {
    suspend fun getMovies(refresh: Boolean): List<MovieDto>
    suspend fun getMovieDetails(id: Int): MovieDto?
    suspend fun getGenres(): List<GenreDto>
}