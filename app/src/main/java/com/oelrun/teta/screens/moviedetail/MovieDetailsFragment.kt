package com.oelrun.teta.screens.moviedetail

import android.animation.ObjectAnimator
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.*
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.oelrun.teta.R
import com.oelrun.teta.adapters.CastAdapter
import com.oelrun.teta.adapters.decorators.CastItemDecorator
import com.oelrun.teta.database.entities.relations.MovieFullInfo
import com.oelrun.teta.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: MovieDetailsViewModel by viewModels()
    private lateinit var castAdapter: CastAdapter
    private var movieId = 0
    private var orientationPortrait = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        orientationPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        if(orientationPortrait) {
            val windowHeight = resources.displayMetrics.heightPixels
            binding.imagePoster.minimumHeight = windowHeight * 3 / 2
            if (savedInstanceState != null) {
                slideBottomSheet()
            }
        } else {
            val windowHeight = resources.displayMetrics.heightPixels
            binding.imagePoster.layoutParams.height = windowHeight
            binding.imagePoster.layoutParams.width = windowHeight * 2 / 3
        }

        sharedElementEnterTransition = TransitionSet().apply {
            addTransition(TransitionInflater.from(requireContext())
                .inflateTransition(android.R.transition.move))
                .addListener(object: Transition.TransitionListener {
                    override fun onTransitionStart(transition: Transition) {}

                    override fun onTransitionEnd(transition: Transition) {
                        if(orientationPortrait)  slideBottomSheet()
                    }
                    override fun onTransitionCancel(transition: Transition) {}
                    override fun onTransitionPause(transition: Transition) {}
                    override fun onTransitionResume(transition: Transition) {}
                })
            duration = 600
        }

        sharedElementReturnTransition = TransitionInflater.from(this.context)
            .inflateTransition(android.R.transition.move)

        movieId = MovieDetailsFragmentArgs.fromBundle(requireArguments()).idMovie
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
                    resources.getString(R.string.movie_detail_error_message),
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        return binding.root
    }

    private fun slideBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet as View)
        bottomSheetBehavior.setPeekHeight(0, false)
        binding.bottomSheet?.visibility = View.VISIBLE
        ObjectAnimator.ofInt(bottomSheetBehavior, "peekHeight",
            800)
            .apply {
                duration = 800
                start()
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imagePoster.transitionName = "transition_image$movieId"
        binding.movieTitle.transitionName = "transition_title$movieId"
        binding.movieDescription.transitionName = "transition_about$movieId"
        binding.movieRating.transitionName = "transition_rating$movieId"
        binding.movieAgeLevel.transitionName = "transition_age$movieId"

        postponeEnterTransition()
        binding.imagePoster.post { startPostponedEnterTransition() }
    }

    private fun showDetails(item: MovieFullInfo) {
        val movie = item.movie
        binding.imagePoster.load(
            "https://www.themoviedb.org/t/p/original${movie.imageUrl}") {
            allowHardware(false)
            error(R.drawable.broken_image)
            crossfade(true)
        }
        binding.movieGenreName.visibility = View.VISIBLE

        var genreName: String? = null
        item.genres?.let { if (it.isNotEmpty()) genreName = it[0].name }

        binding.movieGenreName.text = genreName?.lowercase() ?: "undefined"

        binding.movieData.text = movie.releaseDate
        binding.movieAgeLevel.text = item.movie.ageRestriction
        binding.movieTitle.text = movie.title
        binding.movieDescription.text = movie.description
        binding.movieRating.apply {
            this.rating = movie.rateScore
            invalidate()
        }

        item.actors?.let { castAdapter.list = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}