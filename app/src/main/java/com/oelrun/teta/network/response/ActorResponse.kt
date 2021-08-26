package com.oelrun.teta.network.response

import com.oelrun.teta.database.entities.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorResponse (
    @SerialName("id")
    val actorId: Int,
    @SerialName("name")
    val actorName: String,
    @SerialName("profile_path")
    val imagePath: String?
)

fun ActorResponse.convertToActorEntity() = Actor(actorId, actorName, imagePath)
