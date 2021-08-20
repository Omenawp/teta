package com.oelrun.teta.database.entities

import androidx.room.*

@Entity
data class Movie(
    @PrimaryKey
    val movieId: Int,
    val title: String,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val releaseDate: String,
    val imageUrl: String
)