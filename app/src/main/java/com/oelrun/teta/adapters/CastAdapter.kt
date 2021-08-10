package com.oelrun.teta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.adapters.viewholders.CastViewHolder
import com.oelrun.teta.data.movie.PersonDto
import com.oelrun.teta.databinding.ListItemCastBinding

class CastAdapter: RecyclerView.Adapter<CastViewHolder>() {

    var list = listOf<PersonDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemCastBinding.inflate(layoutInflater, parent, false)
        return CastViewHolder(binding)
    }
}