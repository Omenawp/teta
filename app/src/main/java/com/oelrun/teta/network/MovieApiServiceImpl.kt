package com.oelrun.teta.network

import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.data.genre.GenresDataSource
import com.oelrun.teta.data.movie.MovieDto
import com.oelrun.teta.data.movie.MoviesDataSource

class MovieApiServiceImpl: MovieApiService {
    override suspend fun getMovies(refresh: Boolean): List<MovieDto> {
        return MoviesDataSource().getMovies(refresh)
    }

    override suspend fun getMovieDetails(id: Int): MovieDto? {
        return MoviesDataSource().getMovieById(id)
    }

    override suspend fun getGenres(): List<GenreDto> {
        return GenresDataSource().getGenres()
    }
}

object MovieApi {
    val repository = MovieApiServiceImpl()
}