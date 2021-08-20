package com.oelrun.teta.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseResponse (
    @SerialName("certification")
    val certification: String
)