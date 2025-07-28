package com.iosgallery.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.iosgallery.android.databinding.ItemPersonBinding
import com.iosgallery.android.model.Person

class PersonAdapter(
    private val people: List<Person>,
    private val onPersonClick: (Person) -> Unit
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(private val binding: ItemPersonBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(person: Person) {
            Glide.with(binding.root.context)
                .load(person.coverPhoto.path)
                .transform(CenterCrop())
                .into(binding.ivPersonPhoto)
            
            binding.root.setOnClickListener {
                onPersonClick(person)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(people[position])
    }

    override fun getItemCount(): Int = people.size
}