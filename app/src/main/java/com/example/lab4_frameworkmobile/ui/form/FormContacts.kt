package com.example.lab4_frameworkmobile.ui.form

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.databinding.FragmentFormularioContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FormContacts : BaseFragment<FragmentFormularioContactsBinding>() {
    private val formContactsViewModel: FormContactsViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun inflateBinding() {
        binding = FragmentFormularioContactsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding?.getLocationButton?.setOnClickListener {
            getLocation()
        }

        binding?.btnEnviar?.setOnClickListener {
            val nombre = binding?.etNombre?.text?.toString() ?: ""
            val ciudadFavorita = binding?.etCiudadFavorita?.text?.toString() ?: ""
            val fechaNacimiento = binding?.etFechaNacimiento?.text?.toString() ?: ""
            val numeroFavorito = binding?.etNumeroFavorito?.text?.toString() ?: ""
            val colorPreferido = binding?.etColorPreferido?.text?.toString() ?: ""
            val latitud = binding?.etLatitud?.text?.toString() ?: ""
            val longitud = binding?.etLongitud?.text?.toString() ?: ""

            if (nombre.isEmpty() || ciudadFavorita.isEmpty() || fechaNacimiento.isEmpty() ||
                numeroFavorito.isEmpty() || colorPreferido.isEmpty() || latitud.isEmpty() || longitud.isEmpty()
            ) {
                Log.e(TAG, "Todos los campos deben ser completados")
                return@setOnClickListener
            }

            val user = UserEntity(
                0,
                nombre,
                ciudadFavorita,
                fechaNacimiento,
                numeroFavorito,
                colorPreferido,
                "$latitud, $longitud"
            )
            insertUser(user)
        }
    }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        binding?.etLatitud?.setText(it.latitude.toString())
                        binding?.etLongitud?.setText(it.longitude.toString())
                    } ?: run {
                        Log.e(TAG, "No se pudo obtener la ubicación")
                    }
                }
                .addOnFailureListener {
                    Log.e(TAG, "Error al obtener la ubicación: ${it.message}")
                }
        }
    }

    private fun insertUser(user: UserEntity) {
        lifecycleScope.launch {
            try {
                formContactsViewModel.insertUser(user)
                findNavController().navigate(
                    FormContactsDirections.actionFormularioContactsToContactsFragment()
                )
            } catch (e: Exception) {
                Log.e(TAG, "Error inserting user: ${e.message}")
            }
        }
    }

    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() {
        fragmentLayoutWithToolbar()
        showToolbar(title = "Form ", showBack = true)
    }
}
