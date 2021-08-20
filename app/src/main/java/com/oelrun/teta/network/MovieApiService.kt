package com.oelrun.teta.network

import com.oelrun.teta.data.dto.convertToMovieEntity
import com.oelrun.teta.data.source.CastDataSource
import com.oelrun.teta.data.source.GenresDataSource
import com.oelrun.teta.data.source.MoviesDataSource
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.relations.MovieFullInfo

class MovieApiService {
    fun getMovies(refresh: Boolean): List<MovieFullInfo> {
        return MoviesDataSource().getMovies(refresh).map {
            MovieFullInfo(
                it.convertToMovieEntity(),
                CastDataSource().getCastByMovieId(it.id).cast,
                it.genres
            )
        }
    }

    fun getMovieDetails(id: Int): MovieFullInfo? {
        return MoviesDataSource().getMovieById(id)?.let {
            MovieFullInfo(
                it.convertToMovieEntity(),
                CastDataSource().getCastByMovieId(it.id).cast,
                it.genres
            )
        }
    }

    fun getGenres(): List<Genre> {
        return GenresDataSource().getGenres()
    }
}

object MovieApi {
    val webservice = MovieApiService()
}