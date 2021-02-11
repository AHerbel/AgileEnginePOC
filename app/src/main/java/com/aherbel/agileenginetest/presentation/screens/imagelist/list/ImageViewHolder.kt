package com.aherbel.agileenginetest.presentation.screens.imagelist.list

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aherbel.agileenginetest.databinding.ItemImageBinding
import com.aherbel.agileenginetest.domain.model.ImagePlaceholder

class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
    
    fun bind(
        imagePlaceholder: ImagePlaceholder,
        onImageClickListener: (image: ImagePlaceholder) -> Unit
    ) {
        binding.croppedImage.load(imagePlaceholder.croppedPicture) {
            crossfade(true)
        }
        binding.croppedImage.setOnClickListener { onImageClickListener.invoke(imagePlaceholder) }
    }
    
}