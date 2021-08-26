package com.oelrun.teta.screens.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.R
import com.oelrun.teta.adapters.GenresAdapter
import com.oelrun.teta.adapters.MoviesAdapter
import com.oelrun.teta.adapters.MoviesListener
import com.oelrun.teta.adapters.decorators.GenresItemDecoration
import com.oelrun.teta.adapters.decorators.MoviesItemDecoration
import com.oelrun.teta.database.entities.Genre
import com.oelrun.teta.databinding.FragmentMoviesBinding

class MoviesFragment: Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterGenres: GenresAdapter
    private lateinit var adapterMovies: MoviesAdapter
    private var moviesFragmentClickListener: MoviesFragmentClickListener? = null
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
            //if(data.isNotEmpty()) {
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
            //}
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

        return binding.root
    }

    private fun setupGenresAdapter() {
        adapterGenres = GenresAdapter()
        val onItemGenresClicked = { item: Genre ->
            //Toast.makeText(this.context, item.name, Toast.LENGTH_SHORT).show()
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

        adapterMovies = MoviesAdapter(MoviesListener { id ->
            moviesFragmentClickListener?.navigateToDetail(id)
        })

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MoviesFragmentClickListener){
            moviesFragmentClickListener = context
        }
    }

    override fun onPause() {
        moviesViewModel.savePosition(binding.listMovies.layoutManager as GridLayoutManager)
        super.onPause()
    }

    override fun onDetach() {
        super.onDetach()
        moviesFragmentClickListener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface MoviesFragmentClickListener {
    fun navigateToDetail(id: Int)
}
