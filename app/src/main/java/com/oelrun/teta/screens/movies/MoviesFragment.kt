package com.oelrun.teta.screens.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.R
import com.oelrun.teta.adapters.GenresAdapter
import com.oelrun.teta.adapters.MoviesAdapter
import com.oelrun.teta.adapters.decorators.GenresItemDecoration
import com.oelrun.teta.adapters.decorators.MoviesItemDecoration
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.databinding.FragmentMoviesBinding

class MoviesFragment: Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterGenres: GenresAdapter
    private lateinit var adapterMovies: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        setupMoviesAdapter()
        setupGenresAdapter()

        binding.swipeContainerMovies.setOnRefreshListener {
            moviesViewModel.loadMovies(true)
        }

        moviesViewModel.isRefreshing.observe(viewLifecycleOwner, {
            binding.swipeContainerMovies.isRefreshing = it
        })

        moviesViewModel.moviesData.observe(viewLifecycleOwner, { data ->
            binding.listMovies.visibility = View.VISIBLE
            binding.errorMessage.visibility = View.GONE
            adapterMovies.addHeaderAndSubmitList(data) {
                binding.listMovies.post {
                    binding.listMovies.invalidateItemDecorations()
                }
                if (moviesViewModel.firstItemMovie != -1) {
                    binding.listMovies.scrollToPosition(moviesViewModel.firstItemMovie)
                }
            }
        })

        moviesViewModel.errorMessage.observe(viewLifecycleOwner, { message ->
            message?.let {
                Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
                moviesViewModel.errorMessageShown()
                if(moviesViewModel.moviesData.value?.isEmpty() == true) {
                    binding.listMovies.visibility = View.GONE
                    binding.errorMessage.visibility = View.VISIBLE
                }
            }
        })

        moviesViewModel.genresData.observe(viewLifecycleOwner, { data ->
            if(data.isNotEmpty()) {
                adapterGenres.list = data
                binding.listGenres.post {
                    binding.listGenres.invalidateItemDecorations()
                }
            }
        })

        binding.listMovies.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = binding.listMovies.layoutManager as GridLayoutManager
                val total = lm.itemCount
                val last = lm.findLastVisibleItemPosition()
                if(total - last == 5) {
                    if(moviesViewModel.isRefreshing.value == false) {
                        moviesViewModel.loadNext()
                    }
                }
            }
        })

        val imm = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.search.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                val search = textView.text.toString()
                moviesViewModel.movieSearch(search)
                textView.clearFocus()
                imm.hideSoftInputFromWindow(textView.windowToken, 0)
            }
            true
        }

        binding.searchImage.setOnClickListener {
            if(moviesViewModel.searchShown) {
                imm.hideSoftInputFromWindow(binding.search.windowToken, 0)
                moviesViewModel.movieSearch(null)
                binding.search.setText("")
                binding.search.clearFocus()
                binding.search.visibility = View.INVISIBLE
                binding.searchImage.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
            } else {
                binding.search.visibility = View.VISIBLE
                binding.search.requestFocus()
                imm.showSoftInput(binding.search, 0)
                binding.searchImage.setImageDrawable(resources.getDrawable(R.drawable.ic_clear))
            }

            moviesViewModel.changeSearchVisibility()
            adapterGenres.notifyDataSetChanged()
        }

        if(moviesViewModel.searchShown) {
            binding.search.setText(moviesViewModel.search)
            binding.search.visibility = View.VISIBLE
            binding.searchImage.setImageDrawable(resources.getDrawable(R.drawable.ic_clear))
        }

        return binding.root
    }

    private fun setupGenresAdapter() {
        adapterGenres = GenresAdapter()
        val onItemGenresClicked = { item: Genre ->
            moviesViewModel.genreChangeSelection(item)
            adapterGenres.notifyDataSetChanged()
        }
        adapterGenres.clickListener = onItemGenresClicked
        binding.listGenres.adapter = adapterGenres
        binding.listGenres.addItemDecoration(GenresItemDecoration())
    }

    private fun setupMoviesAdapter() {
        val windowWidth = resources.displayMetrics.widthPixels
        val space = windowWidth - resources.getDimension(R.dimen.margin_main)
        val spanWidthMin = resources.getDimension(R.dimen.item_movie_width) +
                resources.getDimension(R.dimen.margin_main)
        val spanCount = (space / spanWidthMin).toInt()

        val occupiedWidth = windowWidth - resources.getDimension(R.dimen.item_movie_width) *
                spanCount - resources.getDimension(R.dimen.margin_main) * 2
        val itemOffset = occupiedWidth / (spanCount - 1) / 2

        val manager = GridLayoutManager(this.context, spanCount)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(position){
                    0 -> spanCount
                    else -> 1
                }
            }
        }

        adapterMovies = MoviesAdapter { id, itemMovieBinding ->
            val extras = FragmentNavigatorExtras(
                itemMovieBinding.posterImage to itemMovieBinding.posterImage.transitionName,
                itemMovieBinding.movieTitle to itemMovieBinding.movieTitle.transitionName,
                itemMovieBinding.movieDescription to itemMovieBinding.movieDescription.transitionName,
                itemMovieBinding.ratingView to itemMovieBinding.ratingView.transitionName,
                itemMovieBinding.ageLevel to itemMovieBinding.ageLevel.transitionName
            )

            this.findNavController().navigate(
                MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(id),
                extras
            )
        }

        binding.listMovies.layoutManager = manager
        binding.listMovies.adapter = adapterMovies
        binding.listMovies.addItemDecoration(
            MoviesItemDecoration(
                spanCount,
                itemOffset.toInt(),
                resources.getDimension(R.dimen.margin_main).toInt(),
                resources.getDimension(R.dimen.item_movie_margin_bottom).toInt())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding.listMovies.post { startPostponedEnterTransition() }
    }

    override fun onPause() {
        moviesViewModel.savePosition(binding.listMovies.layoutManager as GridLayoutManager)
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}