package com.oelrun.teta.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.oelrun.teta.database.entities.Actor
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie

data class MovieFullInfo(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "actorId",
        associateBy = Junction(MovieActorCrossRef::class)
    )
    val actors: List<Actor>?,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val genres: List<Genre>?
)