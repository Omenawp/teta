package com.oelrun.teta.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.R
import com.oelrun.teta.data.genre.GenreDto

class GenresAdapter: RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    lateinit var clickListener: (item: GenreDto) -> Unit
    var list = listOf<GenreDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder (private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(clickListener: (item: GenreDto) -> Unit, item: GenreDto) {
            view as TextView
            view.text = item.name.lowercase()
            view.setBackgroundResource(
                if(item.selected) R.drawable.bgr_genre_selected else R.drawable.bgr_genre
            )
            view.setTextColor(
                if(item.selected) Color.WHITE else Color.BLACK
            )

            view.setOnClickListener {
                clickListener(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_genre, parent, false)

                return ViewHolder(view)
            }
        }
    }
}