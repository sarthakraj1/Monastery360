package com.monastery360.tourism.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.monastery360.tourism.R
import com.monastery360.tourism.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        binding.apply {
            setup3DModelPlaceholder()
        }
    }

    private fun setup3DModelPlaceholder() {
        binding.apply {
            // Get the FrameLayout inside your placeholder
            val frameLayout = placeholder3dView.getChildAt(0) as? FrameLayout
            frameLayout?.removeAllViews()

            // Vertical layout
            val placeholderView = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                gravity = android.view.Gravity.CENTER
                setPadding(40, 40, 40, 40)
            }

            // Monastery icon
            val iconView = TextView(requireContext()).apply {
                text = "🏛️"
                textSize = 60f
                gravity = android.view.Gravity.CENTER
            }

            // Title
            val titleView = TextView(requireContext()).apply {
                text = "3D Monastery Model"
                textSize = 18f
                setTextColor(android.graphics.Color.WHITE)
                gravity = android.view.Gravity.CENTER
                setPadding(0, 16, 0, 8)
            }

            // Subtitle
            val subtitleView = TextView(requireContext()).apply {
                text = "Tap the button below to view in full screen"
                textSize = 14f
                setTextColor(android.graphics.Color.WHITE)
                gravity = android.view.Gravity.CENTER
                alpha = 0.8f
                setPadding(0, 0, 0, 16)
            }

            // Button to open 3D viewer
            val viewButton = Button(requireContext()).apply {
                text = "View 3D Model"
                setBackgroundColor(android.graphics.Color.parseColor("#3B82F6"))
                setTextColor(android.graphics.Color.WHITE)
                setPadding(0, 16, 0, 16)
                setOnClickListener {
                    try {
                        val intent = Intent(requireContext(), ModelViewerActivity::class.java)
                        startActivity(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            requireContext(),
                            "Failed to open 3D viewer",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            // Add all views to layout
            placeholderView.addView(iconView)
            placeholderView.addView(titleView)
            placeholderView.addView(subtitleView)
            placeholderView.addView(viewButton)

            // Add placeholder to FrameLayout
            frameLayout?.addView(placeholderView)
        }
    }

    private fun setupClickListeners() {
        binding.mapButton.setOnClickListener {
            val uri = Uri.parse("geo:39.7142,21.6308?q=Meteora+Monasteries")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=Meteora+Monasteries")))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


