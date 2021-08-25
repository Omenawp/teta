package com.oelrun.teta.network

import com.oelrun.teta.network.response.ObjectAgeResponse
import com.oelrun.teta.network.response.ObjectCastResponse
import com.oelrun.teta.network.response.ObjectGenreResponse
import com.oelrun.teta.network.response.ObjectMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("region") region: String = "ru",
        @Query("page") page: Int
    ): ObjectMoviesResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("region") region: String = "ru",
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): ObjectMoviesResponse

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(@Path("id") movieId: Int): ObjectCastResponse

    @GET("movie/{id}/release_dates")
    suspend fun getMovieAgeRestriction(@Path("id") movieId: Int): ObjectAgeResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): ObjectGenreResponse
}