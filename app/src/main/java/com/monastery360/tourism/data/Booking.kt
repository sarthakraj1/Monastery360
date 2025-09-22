package com.monastery360.tourism.data

import java.util.Date

data class Booking(
    val id: String,
    val monasteryId: Int,
    val monasteryName: String,
    val date: Date,
    val time: String,
    val duration: Int, // in minutes
    val visitorName: String,
    val visitorEmail: String,
    val visitorPhone: String,
    val groupSize: Int,
    val specialRequests: String? = null,
    val status: BookingStatus = BookingStatus.PENDING
)

enum class BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED
}

data class TimeSlot(
    val time: String,
    val available: Boolean,
    val maxCapacity: Int = 20
)
