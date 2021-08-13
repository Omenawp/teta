package com.oelrun.teta.database.dao

import androidx.room.*
import com.oelrun.teta.database.entities.Profile
import com.oelrun.teta.database.entities.relations.MovieActorCrossRef
import com.oelrun.teta.database.entities.relations.ProfileGenreCrossRef
import com.oelrun.teta.database.entities.relations.ProfileWithGenres

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg profile: Profile)

    @Transaction
    @Query("SELECT * FROM profile")
    suspend fun getProfileWithGenres(): ProfileWithGenres?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfileGenreCrossRef(vararg crossRef: ProfileGenreCrossRef)

    @Transaction
    suspend fun deleteProfile() {
        clearProfile()
        clearCrossRef()
    }

    @Query("DELETE FROM profile")
    suspend fun clearProfile()

    @Query("DELETE FROM profilegenrecrossref")
    suspend fun clearCrossRef()
}