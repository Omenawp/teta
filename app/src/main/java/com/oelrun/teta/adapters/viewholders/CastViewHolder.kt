package com.oelrun.teta.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.database.entities.Actor
import com.oelrun.teta.databinding.ListItemCastBinding

class CastViewHolder(private val binding: ListItemCastBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Actor) {
        binding.personName.text = item.actorName
        if(item.imageUrl != null) {
            binding.personPhoto.load(
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2${item.imageUrl}") {
                error(R.drawable.broken_image)
                crossfade(true)
            }
        } else {
            binding.personPhoto.setImageResource(R.drawable.image_cast_photo_empty)
        }
    }
}