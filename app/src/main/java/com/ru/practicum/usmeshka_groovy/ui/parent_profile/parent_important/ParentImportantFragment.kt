package com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_important

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practicum.usmeshka_groovy.databinding.ParentImportantFragmentBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class ParentImportantFragment : Fragment() {
    private var _binding: ParentImportantFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ParentImportantFragmentBinding.inflate(inflater, container, false)
        mapView = binding.mapview
        moveToStartLocation()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appointment.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.103.by/cat/med/stomatologii/")
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun moveToStartLocation() {
        val startLocation = Point(53.89946605938956, 27.55944631275795)
        val zoomValue: Float = 12f
        binding.mapview.map.move(
            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f)
        )
    }

}