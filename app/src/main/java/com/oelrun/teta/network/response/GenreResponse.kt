package com.oelrun.teta.network.response

import com.oelrun.teta.database.entities.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse (
    @SerialName("id")
    val genreId: Int,
    @SerialName("name")
    val name: String
)

fun GenreResponse.convertToGenreEntity() = Genre(genreId, name)