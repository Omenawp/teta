package com.oelrun.teta.data.dto

import com.oelrun.teta.database.entities.Actor

data class CastDto (
    val movieId: Int,
    val cast: List<Actor>
)