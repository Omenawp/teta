package com.oelrun.teta.database.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "actorId"])
data class MovieActorCrossRef(
    val movieId: Int,
    val actorId: Int
)