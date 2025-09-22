package com.monastery360.tourism.ui.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monastery360.tourism.data.Booking
import com.monastery360.tourism.data.BookingStatus
import com.monastery360.tourism.databinding.ItemBookingBinding
import java.text.SimpleDateFormat
import java.util.*

class BookingAdapter(
    private val onItemClick: (Booking) -> Unit
) : ListAdapter<Booking, BookingAdapter.BookingViewHolder>(BookingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val binding = ItemBookingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookingViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookingViewHolder(
        private val binding: ItemBookingBinding,
        private val onItemClick: (Booking) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(booking: Booking) {
            binding.apply {
                monasteryName.text = booking.monasteryName
                visitorName.text = booking.visitorName
                groupSize.text = "Group: ${booking.groupSize}"
                
                val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                bookingDate.text = formatter.format(booking.date)
                
                val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                bookingTime.text = timeFormatter.format(booking.date)
                
                // Set status color and text
                when (booking.status) {
                    BookingStatus.PENDING -> {
                        statusText.text = "Pending"
                        statusText.setTextColor(binding.root.context.getColor(android.R.color.holo_orange_dark))
                    }
                    BookingStatus.CONFIRMED -> {
                        statusText.text = "Confirmed"
                        statusText.setTextColor(binding.root.context.getColor(android.R.color.holo_green_dark))
                    }
                    BookingStatus.CANCELLED -> {
                        statusText.text = "Cancelled"
                        statusText.setTextColor(binding.root.context.getColor(android.R.color.holo_red_dark))
                    }
                    BookingStatus.COMPLETED -> {
                        statusText.text = "Completed"
                        statusText.setTextColor(binding.root.context.getColor(android.R.color.holo_blue_dark))
                    }
                }

                // Set click listener
                root.setOnClickListener {
                    onItemClick(booking)
                }
            }
        }
    }

    class BookingDiffCallback : DiffUtil.ItemCallback<Booking>() {
        override fun areItemsTheSame(oldItem: Booking, newItem: Booking): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Booking, newItem: Booking): Boolean {
            return oldItem == newItem
        }
    }
}
