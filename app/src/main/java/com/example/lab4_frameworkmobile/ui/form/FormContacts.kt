package com.example.lab4_frameworkmobile.ui.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.databinding.FragmentFormularioContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import kotlinx.coroutines.launch

class FormContacts : BaseFragment<FragmentFormularioContactsBinding>() {
    private val formContactsViewModel: FormContactsViewModel by viewModels()
    override fun inflateBinding() {
        binding = FragmentFormularioContactsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) {
        binding?.btnEnviar?.setOnClickListener {
            val user = UserEntity(
                0,
                binding?.etNombre?.text.toString(),
                binding?.etCiudadFavorita?.text.toString(),
                binding?.etFechaNacimiento?.text.toString(),
                binding?.etNumeroFavorito?.text.toString(),
                binding?.etColorPreferido?.text.toString(),
                binding?.etLocalizacionActual?.text.toString()
            )
            insertUser(user)
        }
    }

    private fun insertUser(user: UserEntity) {
        lifecycleScope.launch {
            try {
                findNavController().navigate(
                    FormContactsDirections.actionFormularioContactsToContactsFragment()
                )
                formContactsViewModel.insertUser(user)
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
        showToolbar(title = ("Form "), showBack = true)
    }

}