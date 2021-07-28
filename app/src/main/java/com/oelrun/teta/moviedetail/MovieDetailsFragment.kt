package com.oelrun.teta.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.data.movie.MoviesDataSource
import com.oelrun.teta.utils.RatingView

class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)

        val movieId = arguments?.getInt(CURRENT_ITEM)
        val movie = MoviesDataSource().getMovieById(movieId)

        if(movie != null) {
            val ageLevel = "${movie.ageRestriction}+"

            view.findViewById<ImageView>(R.id.image_poster).load(movie.imageUrl)
            view.findViewById<TextView>(R.id.movie_genre_name).text = movie.genre[0].name.lowercase()
            view.findViewById<TextView>(R.id.movie_data).text = movie.releaseDate
            view.findViewById<TextView>(R.id.movie_age_level).text = ageLevel
            view.findViewById<TextView>(R.id.movie_title).text = movie.title
            view.findViewById<RatingView>(R.id.movie_rating).rating = movie.rateScore
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

        } else {
            Toast.makeText(this.context,
                resources.getString(R.string.movie_detail_error_message), Toast.LENGTH_LONG).show()
            parentFragmentManager.popBackStack()
        }

        return view
    }

    companion object {
        private const val CURRENT_ITEM = "key_current_item_id"

        fun newInstance(id: Int): MovieDetailsFragment {
            val args = Bundle()
            args.putInt(CURRENT_ITEM, id)
            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}