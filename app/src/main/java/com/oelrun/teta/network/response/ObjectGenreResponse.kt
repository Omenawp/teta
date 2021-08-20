package com.oelrun.teta.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObjectGenreResponse (
    @SerialName("genres")
    val genres: List<GenreResponse>? = null,
    @SerialName("status_message")
    val errorMessage: String? = null,
    @SerialName("status_code")
    val errorCode: Int? = null
)