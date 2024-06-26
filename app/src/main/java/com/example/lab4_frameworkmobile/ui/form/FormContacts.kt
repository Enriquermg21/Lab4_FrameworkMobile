package com.example.lab4_frameworkmobile.ui.form

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.databinding.FragmentFormularioContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import com.example.lab4_frameworkmobile.ui.location.LocationService
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


@AndroidEntryPoint
class FormContacts : BaseFragment<FragmentFormularioContactsBinding>() {
    private val formContactsViewModel: FormContactsViewModel by viewModels()
    private var materialDatePicker: MaterialDatePicker<Long>? = null
    private var dateFormat: SimpleDateFormat? = null
    private lateinit var locationService: LocationService

    override fun inflateBinding() {
        binding = FragmentFormularioContactsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) {
        locationService = LocationService(requireContext())
        configButtonEnviar()
        configEditText()
        configDatePicker()
        getLocation()
    }


    private fun getLocation() {

        viewLifecycleOwner.lifecycleScope.launch {
            if (locationService.checkLocationPermission()) {
                locationService.getLocationUpdates().collect { location ->
                    if (location != null) {
                        binding?.btGetLocation?.setOnClickListener {
                            binding?.etLatitude?.text = location.latitude.toString()
                            binding?.etLongitude?.text = location.longitude.toString()

                        }
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


    private fun insertUser(user: UserEntity) {
        lifecycleScope.launch {
            try {
                formContactsViewModel.insertUser(user)
                findNavController().navigate(
                    FormContactsDirections.actionFormularioContactsToContactsFragment()
                )
            } catch (e: Exception) {
                Log.e("InsertUser", "Error inserting user", e)
                Toast.makeText(
                    requireContext(),
                    "Error inserting user: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
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
            when (actionId) {
                EditorInfo.IME_ACTION_NEXT -> {
                    val nextView = binding?.etFavoriteNumber?.focusSearch(View.FOCUS_DOWN)
                    nextView?.requestFocus()
                    true
                }

                EditorInfo.IME_ACTION_DONE -> {
                    hideKeyboard()
                    binding?.etFavoriteNumber?.clearFocus()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")

    private fun configDatePicker() {
        binding?.etBirthDate?.setOnClickListener {
            Log.d("DatePicker", "Button clicked")

            dateFormat = SimpleDateFormat("dd/MM/yyyy")

            materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecciona una fecha")
                .build()

            materialDatePicker!!.addOnPositiveButtonClickListener { selection ->
                val date = Date(selection)
                val selectedDate = dateFormat!!.format(date)
                binding!!.etBirthDate.setText(selectedDate)
                Toast.makeText(
                    requireContext(),
                    "Fecha seleccionada: $selectedDate",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d("DatePicker", "Fecha seleccionada: $selectedDate")
            }
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        Log.d("DatePicker", "Showing date picker dialog")
        materialDatePicker?.show(childFragmentManager, "DATE_PICKER")
    }


    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() {
        fragmentLayoutWithToolbar()
        showToolbar(title = "Form ", showBack = true)
    }
}
