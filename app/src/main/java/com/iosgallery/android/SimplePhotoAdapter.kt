package com.iosgallery.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimplePhotoAdapter(private val photos: List<String>) : 
    RecyclerView.Adapter<SimplePhotoAdapter.PhotoViewHolder>() {
    
    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(android.R.id.text1)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return PhotoViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.textView.text = photos[position]
        holder.textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        holder.textView.setPadding(16, 32, 16, 32)
    }
    
    override fun getItemCount() = photos.size
}