package com.brightstarts.iosgallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class PhotoAdapter(private val photos: List<String>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    
    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        
        Glide.with(holder.itemView.context)
            .load(photo)
            .transform(CenterCrop(), RoundedCorners(24))
            .placeholder(R.drawable.ic_placeholder)
            .into(holder.imageView)
    }
    
    override fun getItemCount(): Int = photos.size
}