package com.monastery360.tourism.ui.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.monastery360.tourism.R
import com.monastery360.tourism.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            // Set up the app icon
            appIcon.setImageResource(R.mipmap.ic_launcher)
            
            // Set up version info
            versionText.text = "Version 1.0.0"
            
            // Set up night mode toggle
            setupNightModeToggle()
            
            // Set up feature highlights
            setupFeatureHighlights()
        }
    }

    private fun setupNightModeToggle() {
        val sharedPrefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isNightMode = sharedPrefs.getBoolean("night_mode", false)
        
        binding.nightModeSwitch.isChecked = isNightMode
        
        binding.nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPrefs.edit()
            editor.putBoolean("night_mode", isChecked)
            editor.apply()
            
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun setupFeatureHighlights() {
        val features = listOf(
            "Immersive 3D monastery experiences",
            "Interactive maps and location services",
            "Detailed monastery information and history",
            "Beautiful, modern user interface",
            "Offline support for key features",
            "Regular updates with new monasteries"
        )
        
        binding.featuresList.text = features.joinToString("\n• ", "• ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
