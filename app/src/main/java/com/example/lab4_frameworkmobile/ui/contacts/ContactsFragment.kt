package com.example.lab4_frameworkmobile.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab4_frameworkmobile.databinding.FragmentContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {
    override fun inflateBinding() {
        binding = FragmentContactsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = Unit

    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() {

        fragmentLayoutWithToolbar()
        showToolbar(title = ("Contacts"), showBack = true)
    }
}
