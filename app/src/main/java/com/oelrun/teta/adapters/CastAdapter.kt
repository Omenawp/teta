package com.oelrun.teta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.data.movie.PersonDto

class CastAdapter: RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    var list = listOf<PersonDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        private val personName: TextView = view.findViewById(R.id.person_name)
        private val personPhoto: ImageView = view.findViewById(R.id.person_photo)

        fun bind(item: PersonDto) {
            personName.text = item.name
            if(item.imageUrl != null) {
                personPhoto.load(item.imageUrl)
            } else {
                personPhoto.setImageResource(R.drawable.image_cast_photo_empty)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_actor, parent, false)

                return ViewHolder(view)
            }
        }
    }
}