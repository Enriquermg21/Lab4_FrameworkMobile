package com.example.lab4_frameworkmobile.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab4_frameworkmobile.databinding.FragmentContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.contacts.adapter.ContactsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {

    private val contactsfragmentViewModel: ContactsFragmentViewModel by viewModels()
    val contactsAdapter = ContactsAdapter()
    override fun inflateBinding() {
        binding = FragmentContactsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        configRecycledView()
        binding?.fab?.setOnClickListener() {
            findNavController().navigate(
                ContactsFragmentDirections.actionContactsFragmentToFormularioContacts()
            )
        }
    }

    private fun configRecycledView() {
        binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = contactsAdapter
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            contactsfragmentViewModel.listLoad.collectLatest {
                configRecycledView()
                TODO()
            }
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() {

        fragmentLayoutWithToolbar()
        showToolbar(title = ("Contacts"), showBack = true)
    }
}
