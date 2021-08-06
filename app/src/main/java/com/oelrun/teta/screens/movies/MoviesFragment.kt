package com.oelrun.teta.screens.movies

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.oelrun.teta.R
import com.oelrun.teta.adapters.GenresAdapter
import com.oelrun.teta.adapters.MoviesAdapter
import com.oelrun.teta.adapters.MoviesListener
import com.oelrun.teta.adapters.decorators.GenresItemDecoration
import com.oelrun.teta.adapters.decorators.MoviesItemDecoration
import com.oelrun.teta.data.genre.GenreDto
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesFragment: Fragment() {

    private lateinit var listMovies: RecyclerView
    private lateinit var listGenres: RecyclerView
    private lateinit var adapterGenres: GenresAdapter
    private lateinit var adapterMovies: MoviesAdapter
    private lateinit var swipeContainer: SwipeRefreshLayout
    private var moviesFragmentClickListener: MoviesFragmentClickListener? = null
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container_movies)
        val messageItem = view.findViewById<TextView>(R.id.error_message)
        listMovies = view.findViewById(R.id.list_movies)
        listGenres = view.findViewById(R.id.list_genres)

        setupMoviesAdapter()
        setupGenresAdapter()

        swipeContainer.setOnRefreshListener {
            moviesViewModel.loadMovies(true)
        }

        lifecycleScope.launch {
            moviesViewModel.isRefreshing.collect {
                swipeContainer.isRefreshing = it
            }
        }

        lifecycleScope.launch {
            moviesViewModel.moviesData.collect { data ->
                data ?: return@collect
                if(data.isEmpty()) {
                    messageItem.visibility = View.VISIBLE
                } else {
                    messageItem.visibility = View.GONE
                    adapterMovies.addHeaderAndSubmitList(data) {
                        Handler(Looper.getMainLooper()).post {
                            listMovies.invalidateItemDecorations()
                        }
                    }
                    if(moviesViewModel.firstItemMovie != -1) {
                        listMovies.smoothScrollToPosition(moviesViewModel.firstItemMovie)
                    }
                }
            }
        }

        lifecycleScope.launch {
            moviesViewModel.errorMessage.collect { message ->
                message?.let {
                    Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
                    moviesViewModel.errorMessageShown()
                }
            }
        }

        lifecycleScope.launch {
            moviesViewModel.genresData.collect { data ->
                if (data.isNotEmpty()) {
                    adapterGenres.list = data
                    Handler(Looper.getMainLooper()).post {
                        listGenres.invalidateItemDecorations()
                    }
                }
            }
        }

        return view
    }

    private fun setupGenresAdapter() {
        adapterGenres = GenresAdapter()
        val onItemGenresClicked = { item: GenreDto ->
            Toast.makeText(this.context, item.name, Toast.LENGTH_SHORT).show()
            moviesViewModel.genreChangeSelection(item)
            adapterGenres.notifyDataSetChanged()
        }
        adapterGenres.clickListener = onItemGenresClicked
        listGenres.adapter = adapterGenres
        listGenres.addItemDecoration(GenresItemDecoration())
    }

    private fun setupMoviesAdapter() {
        val windowWidth = resources.displayMetrics.widthPixels
        val space = windowWidth - resources.getDimension(R.dimen.margin_main)
        val spanWidthMin = resources.getDimension(R.dimen.item_movie_width) +
                resources.getDimension(R.dimen.margin_main)
        val spanCount = (space / spanWidthMin).toInt()

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

        listMovies.layoutManager = manager
        listMovies.adapter = adapterMovies
        listMovies.addItemDecoration(
            MoviesItemDecoration(
                windowWidth,
                spanCount,
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
        val movieLM = listMovies.layoutManager as GridLayoutManager
        var firstItemMovie = movieLM.findFirstCompletelyVisibleItemPosition()
        if(firstItemMovie == -1) {
            firstItemMovie = movieLM.findFirstVisibleItemPosition()
        }
        moviesViewModel.firstItemMovie = firstItemMovie
        swipeContainer.isRefreshing = false
        super.onPause()
    }

    override fun onDetach() {
        super.onDetach()
        moviesFragmentClickListener = null
    }
}

interface MoviesFragmentClickListener {
    fun navigateToDetail(id: Int)
}
