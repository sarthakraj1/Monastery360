package com.monastery360.tourism.ui.monasteries

import android.view.LayoutInflater
import com.monastery360.tourism.R
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monastery360.tourism.data.Monastery
import com.monastery360.tourism.databinding.ItemMonasteryBinding

class MonasteryAdapter(
    private val onItemClick: (Monastery) -> Unit
) : ListAdapter<Monastery, MonasteryAdapter.MonasteryViewHolder>(MonasteryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonasteryViewHolder {
        val binding = ItemMonasteryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MonasteryViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MonasteryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MonasteryViewHolder(
        private val binding: ItemMonasteryBinding,
        private val onItemClick: (Monastery) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(monastery: Monastery) {
            binding.apply {
                monasteryName.text = monastery.name
                monasteryLocation.text = monastery.location
                monasteryDescription.text = monastery.description

                // Load image using Glide
                Glide.with(monasteryImage.context)
                    .load(monastery.imageUrl)
                    .placeholder(R.drawable.placeholder_monastery)
                    .error(R.drawable.placeholder_monastery)
                    .centerCrop()
                    .into(monasteryImage)

                // Set click listener
                root.setOnClickListener {
                    onItemClick(monastery)
                }
            }
        }
    }

    class MonasteryDiffCallback : DiffUtil.ItemCallback<Monastery>() {
        override fun areItemsTheSame(oldItem: Monastery, newItem: Monastery): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Monastery, newItem: Monastery): Boolean {
            return oldItem == newItem
        }
    }
}
