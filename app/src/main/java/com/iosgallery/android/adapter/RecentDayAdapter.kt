package com.iosgallery.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iosgallery.android.databinding.ItemRecentDayBinding
import com.iosgallery.android.model.RecentDay

class RecentDayAdapter(
    private val recentDays: List<RecentDay>,
    private val onRecentDayClick: (RecentDay) -> Unit
) : RecyclerView.Adapter<RecentDayAdapter.RecentDayViewHolder>() {

    inner class RecentDayViewHolder(private val binding: ItemRecentDayBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(recentDay: RecentDay) {
            Glide.with(binding.root.context)
                .load(recentDay.coverPhoto.path)
                .transform(CenterCrop(), RoundedCorners(16))
                .into(binding.ivRecentPhoto)
            
            binding.tvDate.text = recentDay.date
            
            binding.root.setOnClickListener {
                onRecentDayClick(recentDay)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentDayViewHolder {
        val binding = ItemRecentDayBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecentDayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentDayViewHolder, position: Int) {
        holder.bind(recentDays[position])
    }

    override fun getItemCount(): Int = recentDays.size
}