package com.oelrun.teta.screens.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.adapters.CastAdapter
import com.oelrun.teta.adapters.decorators.CastItemDecorator
import com.oelrun.teta.data.movie.MovieDto
import com.oelrun.teta.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: MovieDetailsViewModel by viewModels()
    private lateinit var castAdapter: CastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val movieId = MovieDetailsFragmentArgs.fromBundle(requireArguments()).idMovie
        detailsViewModel.loadDetails(movieId)

        castAdapter = CastAdapter()
        binding.castList.adapter = castAdapter
        binding.castList.addItemDecoration(CastItemDecorator())

        detailsViewModel.movieDetails.observe(viewLifecycleOwner, { details ->
            details?.let {
                binding.detailRootLayout.visibility = View.VISIBLE
                showDetails(details)
            }
            binding.loadingImage.visibility = View.GONE
        })

        detailsViewModel.errorMessage.observe(viewLifecycleOwner, { message ->
            message?.let {
                binding.detailRootLayout.visibility = View.GONE
                Toast.makeText(
                    context,
                    resources.getString(R.string.movie_detail_error_message), Toast.LENGTH_LONG
                ).show()
            }
        })

        return binding.root
    }

    private fun showDetails(movie: MovieDto) {
        val ageLevel = "${movie.ageRestriction}+"
        binding.imagePoster.load(movie.imageUrl)
        binding.movieGenreName.text = movie.genre[0].name.lowercase()
        binding.movieData.text = movie.releaseDate
        binding.movieAgeLevel.text = ageLevel
        binding.movieTitle.text = movie.title
        binding.movieDescription.text = movie.description
        binding.movieRating.apply {
            this.rating = movie.rateScore
            invalidate()
        }

        movie.cast?.let { castAdapter.list = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}