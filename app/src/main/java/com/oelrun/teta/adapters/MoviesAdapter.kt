package com.oelrun.teta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.adapters.viewholders.HeaderViewHolder
import com.oelrun.teta.adapters.viewholders.MovieViewHolder
import com.oelrun.teta.database.entities.Movie
import com.oelrun.teta.databinding.ListItemMovieBinding
import com.oelrun.teta.databinding.ListItemMovieHeaderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesAdapter(private val clickListener: (Int, ListItemMovieBinding) -> Unit):
    ListAdapter<DataItem, RecyclerView.ViewHolder>(MoviesDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Movie>?, callback: () -> Unit) {
        adapterScope.launch {
            val items = if (list.isNullOrEmpty()) ArrayList() else
                listOf(DataItem.Header) + list.map { DataItem.MovieItem(it) }

            withContext(Dispatchers.Main) {
                submitList(items) { callback() }
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
                val item = getItem(position) as DataItem.MovieItem
                holder.bind(clickListener, item.item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMovieHeaderBinding.inflate(layoutInflater, parent, false)
                HeaderViewHolder(binding)
            }
            ITEM_VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
                MovieViewHolder(binding)
            }
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }

}

class MoviesDiffCallback: DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

sealed class DataItem {
    data class MovieItem(val item: Movie): DataItem() {
        override val id = item.movieId
    }

    object Header: DataItem() {
        override val id = Int.MIN_VALUE
    }

    abstract val id: Int
}
