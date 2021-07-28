package com.oelrun.teta.movies

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.R
import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.data.genre.GenresDataSource
import com.oelrun.teta.data.movie.MoviesDataSource

class MoviesFragment: Fragment() {
    private var firstItemMovie = 0
    private var firstItemGenre = 0
    private lateinit var listMovies: RecyclerView
    private lateinit var listGenres: RecyclerView
    private var moviesFragmentClickListener: MoviesFragmentClickListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        listMovies = view.findViewById(R.id.list_movies)

        if(savedInstanceState != null) {
            val posForMovie = savedInstanceState.getInt(FIRST_VISIBLE_ITEM_MOVIE)
            val posForGenre = savedInstanceState.getInt(FIRST_VISIBLE_ITEM_GENRE)
            if(posForMovie != -1) {
                firstItemMovie = posForMovie
            }
            if(posForMovie != -1) {
                firstItemGenre = posForGenre
            }
        }

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
        val adapterMovies = MoviesAdapter(MoviesListener { id ->
            moviesFragmentClickListener?.navigateToDetail(id)
        })
        val moviesData = MoviesDataSource().getMovies()
        val messageItem = view.findViewById<TextView>(R.id.error_message)

        listMovies.layoutManager = manager
        listMovies.adapter = adapterMovies
        listMovies.addItemDecoration(MoviesItemDecoration(
            windowWidth,
            spanCount,
            resources.getDimension(R.dimen.margin_main).toInt(),
            resources.getDimension(R.dimen.item_movie_margin_bottom).toInt()))

        if(moviesData.isEmpty()) {
            messageItem.visibility = View.VISIBLE
        } else {
            messageItem.visibility = View.GONE
            adapterMovies.addHeaderAndSubmitList(moviesData) {
                Handler(Looper.getMainLooper()).post {
                    listMovies.invalidateItemDecorations()
                }
            }
            listMovies.smoothScrollToPosition(firstItemMovie)
        }

        listGenres = view.findViewById(R.id.list_genres)

        val data = GenresDataSource().getGenres()
        val adapterGenres = GenresAdapter()
        val onItemGenresClicked = { item: GenreDto ->
            Toast.makeText(this.context, item.name, Toast.LENGTH_SHORT).show()
            val i = data.indexOf(item)
            data[i].selected = !item.selected
            adapterGenres.list = data
        }
        adapterGenres.clickListener = onItemGenresClicked

        listGenres.adapter = adapterGenres
        adapterGenres.list = data//GenresDataSource().getGenres()
        listGenres.addItemDecoration(GenresItemDecoration())
        listGenres.smoothScrollToPosition(firstItemGenre)

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val movieLM = listMovies.layoutManager as GridLayoutManager
        firstItemMovie = movieLM.findFirstVisibleItemPosition()

        val genreLM = listGenres.layoutManager as LinearLayoutManager
        firstItemGenre = genreLM.findFirstVisibleItemPosition()

        outState.putInt(FIRST_VISIBLE_ITEM_MOVIE, firstItemMovie)
        outState.putInt(FIRST_VISIBLE_ITEM_GENRE, firstItemGenre)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MoviesFragmentClickListener){
            moviesFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        moviesFragmentClickListener = null
    }


    companion object {
        private const val FIRST_VISIBLE_ITEM_MOVIE = "key_first_visible_item_movie"
        private const val FIRST_VISIBLE_ITEM_GENRE = "key_first_visible_item_genre"
    }
}

interface MoviesFragmentClickListener {
    fun navigateToDetail(id: Int)
}
