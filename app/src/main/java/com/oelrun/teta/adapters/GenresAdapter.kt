package com.oelrun.teta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.adapters.viewholders.GenreViewHolder
import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.databinding.ListItemGenreBinding

class GenresAdapter: RecyclerView.Adapter<GenreViewHolder>() {

    lateinit var clickListener: (item: GenreDto) -> Unit
    var list = listOf<GenreDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val item = list[position]
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGenreBinding.inflate(inflater, parent, false)
        return GenreViewHolder(binding)
    }
}