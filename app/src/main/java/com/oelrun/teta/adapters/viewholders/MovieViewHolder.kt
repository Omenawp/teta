package com.oelrun.teta.adapters.viewholders

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.databinding.ListItemMovieBinding

class MovieViewHolder(private val binding: ListItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: (Int, ListItemMovieBinding) -> Unit, movie: Movie) {
        binding.root.animation = AnimationUtils.loadAnimation(binding.root.context,
            android.R.anim.fade_in)
        binding.root.animation.duration = 1000

        binding.movieTitle.text = movie.title
        binding.movieDescription.text = movie.description
        binding.ratingView.rating = movie.rateScore
        binding.posterImage.load(
            "https://www.themoviedb.org/t/p/original${movie.imageUrl}") {
            allowHardware(false)
            error(R.drawable.broken_image)
            crossfade(true)
        }

        val textAge = movie.ageRestriction
        binding.ageLevel.text = textAge

        binding.posterImage.transitionName = "transition_image${movie.movieId}"
        binding.movieTitle.transitionName = "transition_title${movie.movieId}"
        binding.movieDescription.transitionName = "transition_about${movie.movieId}"
        binding.ratingView.transitionName = "transition_rating${movie.movieId}"
        binding.ageLevel.transitionName = "transition_age${movie.movieId}"

        binding.root.setOnClickListener {
            clickListener(movie.movieId, binding)
        }
    }
}