package com.example.lab4_frameworkmobile.ui.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lab4_frameworkmobile.R
import com.example.lab4_frameworkmobile.databinding.FragmentMapBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import com.example.lab4_frameworkmobile.ui.location.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {
    private lateinit var myGoogleMap: GoogleMap
    private val actualLocation = LatLng(0.0, 0.0)
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationService: LocationService

    override fun inflateBinding() {
        binding = FragmentMapBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        configBackButton()
        locationService = LocationService(requireContext())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val mapFragment = childFragmentManager.findFragmentById(R.id.fcvMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        getLocation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (this::myGoogleMap.isInitialized) {
            myGoogleMap.clear()
        }
    }
    private fun configBackButton() {
        binding?.fabBack?.setOnClickListener {
            findNavController().navigate(
                MapFragmentDirections.actionMapFragmentToContactsFragment()
            )
        }
    }

    private fun getLocation() {
        viewLifecycleOwner.lifecycleScope.launch {
            if (locationService.checkLocationPermission()) {
                locationService.getLocationUpdates().collect { location ->
                    if (location != null) {
                        val actualLocation = LatLng(location.latitude, location.longitude)
                        moveToNewLocation(actualLocation)
                    } else {
                        Log.d(TAG, "No se pudo obtener la ubicación.")
                    }
                }
            } else {
                Log.d(TAG, "Permiso ACCESS_FINE_LOCATION denegado.")
                locationService.requestLocationPermission(requireActivity())
            }
        }
    }

    private fun moveToNewLocation(location: LatLng) {
        myGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {

        myGoogleMap = googleMap
        myGoogleMap.uiSettings.isZoomControlsEnabled = true
        myGoogleMap.uiSettings.isMyLocationButtonEnabled = true
        myGoogleMap.uiSettings.isMyLocationButtonEnabled = true
        myGoogleMap.isMyLocationEnabled = true
        myGoogleMap.addMarker(MarkerOptions().position(LatLng(37.0, -121.0)))
        myGoogleMap.addMarker(MarkerOptions().position(LatLng(40.0, -3.0)))


        myGoogleMap.setOnMarkerClickListener {
            myGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it.position, 10f))
            true
        }

        myGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(actualLocation))
        moveToNewLocation(actualLocation)
    }

    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() {
        fragmentLayoutWithToolbar()
        showToolbar(title = "Map", showBack = true)
    }
}
