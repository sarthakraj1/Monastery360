package com.monastery360.tourism.ui.booking

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.monastery360.tourism.R
import com.monastery360.tourism.data.Booking
import com.monastery360.tourism.data.BookingRepository
import com.monastery360.tourism.data.MonasteryRepository
import com.monastery360.tourism.databinding.FragmentBookingBinding
import java.text.SimpleDateFormat
import java.util.*

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookingAdapter: BookingAdapter
    private var selectedDate: Date? = null
    private var selectedMonasteryId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupRecyclerView()
        setupClickListeners()
        loadBookings()
    }

    private fun setupUI() {
        binding.apply {
            // Set up monastery spinner
            val monasteries = MonasteryRepository.getMonasteries()
            val monasteryNames = monasteries.map { it.name }
            // For simplicity, we'll use a text view instead of spinner
            selectedMonasteryName.text = monasteries.first().name
            
            // Set default date to today
            selectedDate = Date()
            updateDateDisplay()
        }
    }

    private fun setupRecyclerView() {
        bookingAdapter = BookingAdapter { booking ->
            // Handle booking click - could show details or edit
            showBookingDetails(booking)
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookingAdapter
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            selectDateButton.setOnClickListener {
                showDatePicker()
            }
            
            selectMonasteryButton.setOnClickListener {
                showMonasterySelector()
            }
            
            bookVisitButton.setOnClickListener {
                createBooking()
            }
            
            calendarButton.setOnClickListener {
                openGoogleCalendar()
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        selectedDate?.let { calendar.time = it }
        
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)
                selectedDate = selectedCalendar.time
                updateDateDisplay()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        
        // Set minimum date to today
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun showMonasterySelector() {
        val monasteries = MonasteryRepository.getMonasteries()
        val monasteryNames = monasteries.map { it.name }.toTypedArray()
        
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Select Monastery")
            .setItems(monasteryNames) { _, which ->
                val selectedMonastery = monasteries[which]
                selectedMonasteryId = selectedMonastery.id
                binding.selectedMonasteryName.text = selectedMonastery.name
            }
            .show()
    }

    private fun updateDateDisplay() {
        selectedDate?.let { date ->
            val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            binding.selectedDateText.text = formatter.format(date)
        }
    }

    private fun createBooking() {
        val visitorName = binding.visitorNameEditText.text.toString().trim()
        val visitorEmail = binding.visitorEmailEditText.text.toString().trim()
        val visitorPhone = binding.visitorPhoneEditText.text.toString().trim()
        val groupSize = binding.groupSizeEditText.text.toString().toIntOrNull() ?: 1
        val specialRequests = binding.specialRequestsEditText.text.toString().trim()

        if (visitorName.isEmpty() || visitorEmail.isEmpty() || visitorPhone.isEmpty()) {
            Toast.makeText(context, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedDate == null) {
            Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show()
            return
        }

        val monastery = MonasteryRepository.getMonasteries().find { it.id == selectedMonasteryId }
        val monasteryName = monastery?.name ?: "Unknown Monastery"

        val booking = Booking(
            id = UUID.randomUUID().toString(),
            monasteryId = selectedMonasteryId,
            monasteryName = monasteryName,
            date = selectedDate!!,
            time = "10:00", // Default time
            duration = 120, // 2 hours
            visitorName = visitorName,
            visitorEmail = visitorEmail,
            visitorPhone = visitorPhone,
            groupSize = groupSize,
            specialRequests = if (specialRequests.isEmpty()) null else specialRequests,
            status = com.monastery360.tourism.data.BookingStatus.PENDING
        )

        BookingRepository.addBooking(booking)
        loadBookings()
        
        // Clear form
        clearForm()
        
        Toast.makeText(context, "Booking created successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun clearForm() {
        binding.apply {
            visitorNameEditText.text?.clear()
            visitorEmailEditText.text?.clear()
            visitorPhoneEditText.text?.clear()
            groupSizeEditText.text?.clear()
            specialRequestsEditText.text?.clear()
        }
    }

    private fun loadBookings() {
        val bookings = BookingRepository.getBookings()
        bookingAdapter.submitList(bookings)
        
        if (bookings.isEmpty()) {
            binding.emptyStateLayout.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyStateLayout.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    private fun showBookingDetails(booking: Booking) {
        val statusText = when (booking.status) {
            com.monastery360.tourism.data.BookingStatus.PENDING -> "Pending"
            com.monastery360.tourism.data.BookingStatus.CONFIRMED -> "Confirmed"
            com.monastery360.tourism.data.BookingStatus.CANCELLED -> "Cancelled"
            com.monastery360.tourism.data.BookingStatus.COMPLETED -> "Completed"
        }

        val formatter = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.getDefault())
        
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Booking Details")
            .setMessage("""
                Monastery: ${booking.monasteryName}
                Date: ${formatter.format(booking.date)}
                Visitor: ${booking.visitorName}
                Group Size: ${booking.groupSize}
                Status: $statusText
                ${if (booking.specialRequests != null) "Special Requests: ${booking.specialRequests}" else ""}
            """.trimIndent())
            .setPositiveButton("OK", null)
            .show()
    }

    private fun openGoogleCalendar() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = android.net.Uri.parse("https://calendar.google.com")
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
