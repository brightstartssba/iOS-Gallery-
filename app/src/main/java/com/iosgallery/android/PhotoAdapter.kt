package com.iosgallery.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class PhotoAdapter(
    private var photos: List<Photo>,
    private val onPhotoClick: (Photo) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.cardPhoto)
        val imageView: ImageView = view.findViewById(R.id.imagePhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        
        // Set placeholder image (gray rectangle)
        holder.imageView.setBackgroundColor(0xFF666666.toInt())
        
        holder.cardView.setOnClickListener {
            onPhotoClick(photo)
        }
    }

    override fun getItemCount() = photos.size

    fun updatePhotos(newPhotos: List<Photo>) {
        photos = newPhotos
        notifyDataSetChanged()
    }
}