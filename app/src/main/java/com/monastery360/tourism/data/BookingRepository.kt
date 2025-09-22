package com.monastery360.tourism.data

import java.util.*

object BookingRepository {
    private val bookings = mutableListOf<Booking>()
    
    fun getBookings(): List<Booking> = bookings.toList()
    
    fun addBooking(booking: Booking) {
        bookings.add(booking)
    }
    
    fun getBookingById(id: String): Booking? {
        return bookings.find { it.id == id }
    }
    
    fun updateBookingStatus(id: String, status: BookingStatus) {
        val booking = bookings.find { it.id == id }
        booking?.let {
            val index = bookings.indexOf(it)
            bookings[index] = it.copy(status = status)
        }
    }
    
    fun getTimeSlots(date: Date): List<TimeSlot> {
        return listOf(
            TimeSlot("09:00", true),
            TimeSlot("10:00", true),
            TimeSlot("11:00", true),
            TimeSlot("12:00", false),
            TimeSlot("13:00", true),
            TimeSlot("14:00", true),
            TimeSlot("15:00", true),
            TimeSlot("16:00", true),
            TimeSlot("17:00", false)
        )
    }
    
    fun getAvailableDates(): List<Date> {
        val calendar = Calendar.getInstance()
        val dates = mutableListOf<Date>()
        
        // Add next 30 days
        for (i in 1..30) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            dates.add(calendar.time)
        }
        
        return dates
    }
}
