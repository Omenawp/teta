package com.oelrun.teta.adapters.viewholders

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.R
import com.oelrun.teta.data.genre.GenreDto
import com.oelrun.teta.databinding.ListItemGenreBinding

class GenreViewHolder(private val binding: ListItemGenreBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: (item: GenreDto) -> Unit, item: GenreDto) {
        val view = binding.root
        view.text = item.name.lowercase()
        view.setBackgroundResource(
            if(item.selected) R.drawable.bgr_genre_selected else R.drawable.bgr_genre
        )
        view.setTextColor(
            if(item.selected) Color.WHITE else Color.BLACK
        )

        view.setOnClickListener { clickListener(item) }
    }
}