package com.oelrun.teta.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oelrun.teta.database.entities.Actor

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg actor: Actor)

    @Query("DELETE FROM actor")
    suspend fun clearActors()
}