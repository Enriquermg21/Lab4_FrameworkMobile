package com.example.lab4_frameworkmobile.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab4_frameworkmobile.databinding.FragmentFormularioContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment

class FormContacts : BaseFragment<FragmentFormularioContactsBinding>() {
    override fun inflateBinding() {
        binding = FragmentFormularioContactsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        binding?.btnEnviar?.setOnClickListener() {

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