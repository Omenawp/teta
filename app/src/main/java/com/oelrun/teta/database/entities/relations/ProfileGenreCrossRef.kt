package com.oelrun.teta.database.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "genreId"])
data class ProfileGenreCrossRef (
    val userId: Int,
    val genreId: Int
)