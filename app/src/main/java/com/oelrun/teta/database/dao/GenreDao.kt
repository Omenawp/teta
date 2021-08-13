package com.oelrun.teta.database.dao

import androidx.room.*
import com.oelrun.teta.database.entities.Genre

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg genre: Genre)

    @Query("SELECT * FROM genre")
    suspend fun getAllGenres(): List<Genre>

    @Transaction
    @Query("DELETE FROM genre WHERE genreId = :genreId")
    suspend fun deleteGeneres(genreId: Int)
}