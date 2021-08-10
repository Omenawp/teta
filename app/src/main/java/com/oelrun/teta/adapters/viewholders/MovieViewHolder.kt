package com.oelrun.teta.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.adapters.MoviesListener
import com.oelrun.teta.data.movie.MovieDto
import com.oelrun.teta.databinding.ListItemMovieBinding

class MovieViewHolder(private val binding: ListItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: MoviesListener, item: MovieDto) {
        binding.movieTitle.text = item.title
        binding.movieDescription.text = item.description
        binding.ratingView.rating = item.rateScore
        binding.posterImage.load(item.imageUrl)
        val textAge = item.ageRestriction.toString() + '+'
        binding.ageLevel.text = textAge

        binding.root.setOnClickListener {
            clickListener.onClick(item)
        }
    }
}