package com.oelrun.teta.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.data.movie.MovieDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class MoviesAdapter(private val clickListener: MoviesListener):
    ListAdapter<DataItem, RecyclerView.ViewHolder>(MoviesDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<MovieDto>?, callback: () -> Unit) {
        adapterScope.launch {
            list ?: return@launch
            if (list.isNotEmpty()) {
                val items = listOf(DataItem.Header) + list.map { DataItem.MovieItem(it) }
                withContext(Dispatchers.Main) {
                    submitList(items) { callback() }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.MovieItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MovieViewHolder -> {
                val movie = getItem(position) as DataItem.MovieItem
                holder.bind(clickListener, movie.movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> MovieViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    /*class MovieViewHolder private constructor(private val view: View) :
        RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.movie_title)
        private val description: TextView = view.findViewById(R.id.movie_description)
        private val rating: RatingView = view.findViewById(R.id.rating_view)
        private val ageLevel: TextView = view.findViewById(R.id.age_level)
        private val posterImage: ImageView = view.findViewById(R.id.poster_image)

        fun bind(clickListener: MoviesListener, item: MovieDto) {
            title.text = item.title
            description.text = item.description
            rating.rating = item.rateScore
            posterImage.load(item.imageUrl)
            val textAge = item.ageRestriction.toString() + '+'
            ageLevel.text = textAge

            view.setOnClickListener { clickListener.onClick(item.title) }
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_movie, parent, false)

                return MovieViewHolder(view)
            }
        }
    }*/

    /*class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view){
        companion object{
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_movie_header, parent, false)
                return HeaderViewHolder(view)
            }
        }
    }*/
}

class MoviesDiffCallback: DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class MoviesListener(val clickListener: (title: String) -> Unit) {
    fun onClick(title: String) = clickListener(title)
}


sealed class DataItem {
    data class MovieItem(val movie: MovieDto): DataItem() {
        override val id = movie.id
    }

    object Header: DataItem() {
        override val id = Int.MIN_VALUE
    }

    abstract val id: Int
}
