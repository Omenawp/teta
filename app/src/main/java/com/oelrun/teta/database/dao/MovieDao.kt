package com.oelrun.teta.database.dao

import androidx.room.*
import com.oelrun.teta.database.entities.*
import com.oelrun.teta.database.entities.relations.MovieActorCrossRef
import com.oelrun.teta.database.entities.relations.MovieFullInfo
import com.oelrun.teta.database.entities.relations.MovieGenreCrossRef

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenreCrossRef(vararg crossRef: MovieGenreCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieActorCrossRef(vararg crossRef: MovieActorCrossRef)

    @Query("SELECT * FROM movie ORDER BY popularity DESC LIMIT 20 OFFSET (:page - 1) * 20")
    suspend fun getPopularMovies(page: Int): List<Movie>

    @Query("SELECT movie.* FROM movie INNER JOIN moviegenrecrossref ON movie.movieId = moviegenrecrossref.movieId WHERE genreId = :genreId ORDER BY popularity DESC LIMIT 20 OFFSET (:page - 1) * 20")
    suspend fun getMoviesByGenre(genreId: Int, page: Int): List<Movie>

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    suspend fun getMovieWithFullInfo(movieId: Int): List<MovieFullInfo>

    @Transaction
    suspend fun deleteMovies() {
        clearMovies()
        clearCrossRefActors()
        clearCrossRefGenres()
    }

    @Query("DELETE FROM movie")
    suspend fun clearMovies()

    @Query("DELETE FROM movieactorcrossref")
    suspend fun clearCrossRefActors()

    @Query("DELETE FROM moviegenrecrossref")
    suspend fun clearCrossRefGenres()
}
