package com.oelrun.teta.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObjectMoviesResponse (
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val movies: List<MovieResponse>? = null,
    @SerialName("status_message")
    val errorMessage: String? = null,
    @SerialName("status_code")
    val errorCode: Int? = null
)