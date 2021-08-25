package com.oelrun.teta.network.response

import com.oelrun.teta.database.entities.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
data class MovieResponse (
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("genre_ids")
    val genres: List<Int>,
    @SerialName("overview")
    val description: String,
    @SerialName("vote_average")
    val vote: Float,
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("poster_path")
    val imageUrl: String,
    @SerialName("popularity")
    val popularity: Float
)

fun MovieResponse.convertToMovieEntity(age: String?): Movie {
    val rateScore = (vote / 2).roundToInt()
    val ageRestriction = age?: if(adult) "18+" else "10+"


    val date = releaseDate.split("-").reversed().joinToString(".")
    return Movie(id, title, description, rateScore, ageRestriction, date, imageUrl, popularity)
}