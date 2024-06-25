package com.example.lab4_frameworkmobile.ui.form

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
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
        configButtonEnviar()
        configEditText()
        getLocation()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun configButtonEnviar() {
        binding?.btnEnviar?.setOnClickListener {
            val name = binding?.etName?.text?.toString() ?: ""
            val dateOfBirth = binding?.etBirthDate?.text?.toString() ?: ""
            val color = binding?.etFavouriteColor?.text?.toString() ?: ""
            val favoriteCity = binding?.etFavoriteCity?.text?.toString() ?: ""
            val favoriteNumber = binding?.etFavoriteNumber?.text?.toString() ?: ""
            val latitude = binding?.etLatitude?.text?.toString() ?: ""
            val longitude = binding?.etLongitude?.text?.toString() ?: ""

            if (name.isEmpty() || dateOfBirth.isEmpty() || color.isEmpty() ||
                favoriteCity.isEmpty() || favoriteNumber.isEmpty() || latitude.isEmpty() || longitude.isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Tienes que completar todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val user = UserEntity(
                0,
                name,
                dateOfBirth,
                color,
                favoriteCity,
                favoriteNumber,
                "$latitude, $longitude"
            )
            insertUser(user)
        }
    }

    private fun getLocation() {
        binding?.btGetLocation?.setOnClickListener {
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

                            binding?.etLatitude?.text = "waraw"//it.latitude.toString()
                            binding?.etLongitude?.text = "ar"//it.longitude.toString()

                        } ?: run {
                            Log.e(TAG, "No se pudo obtener la ubicación")
                        }
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "Error al obtener la ubicación: ${it.message}")
                    }
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

    private fun configEditText() {
        binding?.etName?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding?.etName?.clearFocus()
                true
            } else {
                false
            }
        }
        binding?.etFavouriteColor?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding?.etFavouriteColor?.clearFocus()
                true
            } else {
                false
            }
        }
        binding?.etFavoriteCity?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding?.etFavoriteCity?.clearFocus()
                true
            } else {
                false
            }
        }
        binding?.etFavoriteNumber?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                val nextView = binding?.etFavoriteNumber?.focusSearch(View.FOCUS_DOWN)
                nextView?.requestFocus()
                true
            } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                binding?.etFavoriteNumber?.clearFocus()
                true
            } else {
                false
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
