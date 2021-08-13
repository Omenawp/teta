package com.oelrun.teta.data.dto

import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.database.entities.Movie

data class MovieDto(
    val id: Int,
    val title: String,
    val genres: List<Genre>,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val releaseDate: String,
    val imageUrl: String
) {
    fun convertToMovieEntity() = Movie(
        id, title, description, rateScore, ageRestriction, releaseDate, imageUrl
    )
}