package com.iosgallery.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.widget.ImageView
import com.iosgallery.android.databinding.ItemFullscreenPhotoBinding
import com.iosgallery.android.model.Photo

class FullscreenPhotoAdapter(
    private val photos: List<Photo>,
    private val onPhotoClick: () -> Unit
) : RecyclerView.Adapter<FullscreenPhotoAdapter.FullscreenPhotoViewHolder>() {

    inner class FullscreenPhotoViewHolder(private val binding: ItemFullscreenPhotoBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(photo: Photo) {
            Glide.with(binding.root.context)
                .load(photo.path)
                .into(binding.photoView)
            
            binding.photoView.setOnClickListener {
                onPhotoClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullscreenPhotoViewHolder {
        val binding = ItemFullscreenPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FullscreenPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FullscreenPhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size
}