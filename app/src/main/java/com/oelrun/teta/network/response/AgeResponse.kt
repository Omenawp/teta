package com.oelrun.teta.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgeResponse (
    @SerialName("iso_3166_1")
    val country: String,
    @SerialName("release_dates")
    val data: List<ReleaseResponse>
)