package com.oelrun.teta.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.adapters.MoviesListener
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.databinding.ListItemMovieBinding

class MovieViewHolder(private val binding: ListItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: MoviesListener, movie: Movie) {
        binding.movieTitle.text = movie.title
        binding.movieDescription.text = movie.description
        binding.ratingView.rating = movie.rateScore
        binding.posterImage.load(movie.imageUrl)
        val textAge = movie.ageRestriction.toString() + '+'
        binding.ageLevel.text = textAge

        binding.root.setOnClickListener {
            clickListener.onClick(movie)
        }
    }
}