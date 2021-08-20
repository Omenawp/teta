package com.oelrun.teta.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey
    val genreId: Int,
    val name: String,
    @Ignore
    var selected: Boolean
) {
    constructor(genreId: Int, name: String) :
            this(genreId, name, false)
}
