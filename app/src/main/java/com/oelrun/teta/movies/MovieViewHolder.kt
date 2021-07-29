package com.oelrun.teta.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.data.movie.MovieDto
import com.oelrun.teta.utils.RatingView

class MovieViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.movie_title)
    private val description: TextView = view.findViewById(R.id.movie_description)
    private val rating: RatingView = view.findViewById(R.id.rating_view)
    private val ageLevel: TextView = view.findViewById(R.id.age_level)
    private val posterImage: ImageView = view.findViewById(R.id.poster_image)

    fun bind(clickListener: MoviesListener, item: MovieDto) {
        title.text = item.title
        description.text = ""

        view.doOnLayout {
            setText(item.description, description)
        }

        rating.rating = item.rateScore
        posterImage.load(item.imageUrl)
        val textAge = item.ageRestriction.toString() + '+'
        ageLevel.text = textAge

        view.setOnClickListener { clickListener.onClick(item) }
    }

    private fun setText(text: String, view: TextView) {
        val lines = view.height / view.lineHeight
        val width = view.width
        val paint = view.paint
        var lineLength = 0f
        var whitespace = 0
        var start = 0
        val end = text.length - 1
        var temp = ""
        var count = 0

        run loop@{
            text.forEachIndexed { i, char ->
                if (char == ' ') {
                    whitespace = i
                }
                lineLength += paint.measureText(char.toString())

                if (lineLength >= width) {
                    val t = text.substring(start, whitespace + 1)
                    temp += t
                    count++
                    start = whitespace + 1
                    lineLength -= paint.measureText(t)
                    if(count == lines - 1) {
                        lineLength += paint.measureText("...")
                    }
                }
                if (i == end) {
                    val t = text.substring(start)
                    temp += t
                    count++
                    lineLength = 0f
                }
                if (count == lines) {
                    return@loop
                }
            }
        }

        temp = temp.dropLast(1) + "..."
        view.text = temp
    }
}