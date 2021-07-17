package com.oelrun.teta.movies

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.R
import com.oelrun.teta.data.genre.GenreDto

class GenresAdapter(private val clickListener: GenreListener)
    : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

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

        private var selectedItems = mutableListOf<Int>()

        fun bind(clickListener: GenreListener, item: GenreDto) {
            view as TextView
            view.text = item.name
            view.setOnClickListener {
                clickListener.onClick(item)
                if(selectedItems.contains(item.id)) {
                    view.setBackgroundResource(R.drawable.bgr_genre)
                    view.setTextColor(Color.BLACK)
                    selectedItems.remove(item.id)
                } else {
                    view.setBackgroundResource(R.drawable.bgr_genre_selected)
                    view.setTextColor(Color.WHITE)
                    selectedItems.add(item.id)
                }
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

class GenreListener(val clickListener: (item: GenreDto) -> Unit) {
    fun onClick(item: GenreDto) = clickListener(item)
}
