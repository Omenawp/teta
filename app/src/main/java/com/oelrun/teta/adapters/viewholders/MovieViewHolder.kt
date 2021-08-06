package com.oelrun.teta.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.adapters.MoviesListener
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
        description.text = item.description
        rating.rating = item.rateScore
        posterImage.load(item.imageUrl)
        val textAge = item.ageRestriction.toString() + '+'
        ageLevel.text = textAge
        view.setOnClickListener { clickListener.onClick(item) }
    }
}