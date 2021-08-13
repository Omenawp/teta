package com.oelrun.teta.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Profile

data class ProfileWithGenres(
    @Embedded val profile: Profile,
    @Relation(
        parentColumn = "userId",
        entityColumn = "genreId",
        associateBy = Junction(ProfileGenreCrossRef::class)
    )
    val genres: List<Genre>
)