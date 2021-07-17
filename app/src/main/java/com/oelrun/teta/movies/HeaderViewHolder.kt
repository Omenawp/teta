package com.oelrun.teta.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.R

class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view){
    companion object{
        fun from(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item_movie_header, parent, false)
            return HeaderViewHolder(view)
        }
    }
}