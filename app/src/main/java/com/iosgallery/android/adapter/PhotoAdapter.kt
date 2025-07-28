package com.iosgallery.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iosgallery.android.databinding.ItemPhotoBinding
import com.iosgallery.android.model.Photo

class PhotoAdapter(
    private val photos: List<Photo>,
    private val onPhotoClick: (Photo, Int) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(photo: Photo, position: Int) {
            Glide.with(binding.root.context)
                .load(photo.path)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(binding.ivPhoto)
            
            binding.root.setOnClickListener {
                onPhotoClick(photo, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position], position)
    }

    override fun getItemCount(): Int = photos.size
}