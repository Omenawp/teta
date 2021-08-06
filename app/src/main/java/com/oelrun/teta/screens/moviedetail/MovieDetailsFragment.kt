package com.oelrun.teta.screens.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.adapters.CastAdapter
import com.oelrun.teta.adapters.decorators.CastItemDecorator
import com.oelrun.teta.data.movie.MovieDto
import com.oelrun.teta.utils.RatingView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val movieId = MovieDetailsFragmentArgs.fromBundle(requireArguments()).idMovie

        val detailsViewModel = MovieDetailsViewModel()
        detailsViewModel.loadDetails(movieId)

        lifecycleScope.launch {
            detailsViewModel.movieDetails.collect { details ->
                details?.let {
                    showDetails(details)
                }
            }
        }

        lifecycleScope.launch {
            detailsViewModel.errorMessage.collect { message ->
                message?.let {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.movie_detail_error_message), Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


        return view
    }

    private fun showDetails(movie: MovieDto) {
        view?.let { view ->
            val ageLevel = "${movie.ageRestriction}+"

            //val cornerRadius = resources.getDimension(R.dimen.movie_detail_corner_radius_poster)
            /*{transformations(RoundedCornersTransformation(
                    topLeft = cornerRadius,
                    topRight = cornerRadius))}*/

            view.findViewById<ImageView>(R.id.image_poster).load(movie.imageUrl)
            view.findViewById<TextView>(R.id.movie_genre_name).text = movie.genre[0].name.lowercase()
            view.findViewById<TextView>(R.id.movie_data).text = movie.releaseDate
            view.findViewById<TextView>(R.id.movie_age_level).text = ageLevel
            view.findViewById<TextView>(R.id.movie_title).text = movie.title
            //view.findViewById<RatingView>(R.id.movie_rating).rating = movie.rateScore

            val rateView = view.findViewById<RatingView>(R.id.movie_rating)
            rateView.apply {
                this.rating = movie.rateScore
                invalidate()
            }

            view.findViewById<TextView>(R.id.movie_description).text = movie.description

            val errorMessage = view.findViewById<TextView>(R.id.error_message)
            val castList = view.findViewById<RecyclerView>(R.id.cast_list)
            val adapter = CastAdapter()
            castList.adapter = adapter

            if(movie.cast != null) {
                errorMessage.visibility = View.GONE
                adapter.list = movie.cast
                castList.addItemDecoration(CastItemDecorator())
            } else {
                errorMessage.visibility = View.VISIBLE
            }
        }
    }
}