package com.oelrun.teta

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.data.genre.GenresDataSource
import com.oelrun.teta.data.movie.MoviesDataSource
import com.oelrun.teta.movies.*

const val FIRST_VISIBLE_ITEM_MOVIE = "key_first_visible_item_movie"
const val FIRST_VISIBLE_ITEM_GENRE = "key_first_visible_item_genre"

class MainActivity : AppCompatActivity() {

    private var firstItemMovie = 0
    private var firstItemGenre = 0
    private lateinit var listMovies: RecyclerView
    private lateinit var listGenres: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movies)
        listMovies = findViewById(R.id.list_movies)

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

        val manager = GridLayoutManager(this, spanCount)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(position){
                    0 -> spanCount
                    else -> 1
                }
            }
        }
        val adapterMovies = MoviesAdapter(MoviesListener { title ->
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        })
        val moviesData = MoviesDataSource().getMovies()
        val messageItem = findViewById<TextView>(R.id.error_message)

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

        listGenres = findViewById(R.id.list_genres)
        val adapterGenres = GenresAdapter(GenreListener { item ->
            Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
        })
        listGenres.adapter = adapterGenres
        adapterGenres.list = GenresDataSource().getGenres()
        listGenres.addItemDecoration(GenresItemDecoration())
        listGenres.smoothScrollToPosition(firstItemGenre)
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
}