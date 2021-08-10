package com.oelrun.teta.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oelrun.teta.R
import com.oelrun.teta.data.movie.PersonDto
import com.oelrun.teta.databinding.ListItemCastBinding

class CastViewHolder(private val binding: ListItemCastBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonDto) {
        binding.personName.text = item.name
        if(item.imageUrl != null) {
            binding.personPhoto.load(item.imageUrl)
        } else {
            binding.personPhoto.setImageResource(R.drawable.image_cast_photo_empty)
        }
    }
}