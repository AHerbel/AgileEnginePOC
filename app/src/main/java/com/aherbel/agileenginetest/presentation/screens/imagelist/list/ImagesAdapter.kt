package com.aherbel.agileenginetest.presentation.screens.imagelist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aherbel.agileenginetest.databinding.ItemImageBinding
import com.aherbel.agileenginetest.domain.model.ImagePlaceholder

class ImagesAdapter : RecyclerView.Adapter<ImageViewHolder>() {
    
    interface OnImageClickListener {
        
        fun onImageClicked(image: ImagePlaceholder)
    }
    
    private val items: MutableList<ImagePlaceholder> = mutableListOf()
    
    var onImageClickListener: OnImageClickListener? = null
    
    override fun getItemCount(): Int = items.size
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = items[position]
        holder.bind(image) {
            onImageClickListener?.onImageClicked(it)
        }
    }
    
    fun add(images: List<ImagePlaceholder>) {
        items.addAll(images)
        notifyDataSetChanged()
    }
    
}