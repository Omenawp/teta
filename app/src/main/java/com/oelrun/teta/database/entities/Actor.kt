package com.oelrun.teta.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Actor(
    @PrimaryKey
    val actorId: Int,
    val actorName: String,
    val imageUrl: String?
)