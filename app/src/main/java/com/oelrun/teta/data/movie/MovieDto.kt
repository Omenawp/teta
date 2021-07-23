package com.oelrun.teta.data.movie

import com.oelrun.teta.data.genre.GenreDto

data class MovieDto(
    val id: Int,
    val title: String,
    val genre: List<GenreDto>,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val releaseDate: String,
    val imageUrl: String,
    val cast: List<PersonDto>?
)