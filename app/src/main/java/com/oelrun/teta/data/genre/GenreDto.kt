package com.oelrun.teta.data.genre

data class GenreDto(
    val id: Int,
    val name: String,
    var selected: Boolean = false
)
