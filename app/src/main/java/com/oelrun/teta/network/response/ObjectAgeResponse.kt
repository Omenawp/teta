package com.oelrun.teta.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObjectAgeResponse (
    @SerialName("results")
    val results: List<AgeResponse>? = null,
    @SerialName("status_message")
    val errorMessage: String? = null,
    @SerialName("status_code")
    val errorCode: Int? = null
)