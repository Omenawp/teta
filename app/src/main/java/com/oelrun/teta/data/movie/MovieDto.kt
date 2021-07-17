package com.oelrun.teta.data.movie

data class MovieDto(
    val id: Int,
    val title: String,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String
)