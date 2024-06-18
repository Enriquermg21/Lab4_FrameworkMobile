package com.example.lab4_frameworkmobile.ui.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.databinding.FragmentFormularioContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import com.example.lab4_frameworkmobile.ui.singleton.Singleton.userList

class FormContacts : BaseFragment<FragmentFormularioContactsBinding>() {

    override fun inflateBinding() {
        binding = FragmentFormularioContactsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) {
        binding?.btnEnviar?.setOnClickListener {
            val user = User(
                binding?.etNombre?.text.toString(),
                binding?.etCiudadFavorita?.text.toString(),
                binding?.etFechaNacimiento?.text.toString(),
                binding?.etNumeroFavorito?.text.toString(),
                binding?.etColorPreferido?.text.toString(),
                binding?.etLocalizacionActual?.text.toString()
            )
            Log.d(TAG, "USER FORMCONTACTS${user}")
            userList.add(user)
            findNavController().navigate(
                FormContactsDirections.actionFormularioContactsToContactsFragment()
            )
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