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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsFragment : BaseFragment<FragmentContactsBinding>() {
    private val contactsAdapter = ContactsAdapter(users = emptyList())
    private val contactsFragmentViewModel: ContactsFragmentViewModel by viewModels()
    override fun inflateBinding() {
        binding = FragmentContactsBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        configRecycledView()
        configNavController()
    }

    private fun configRecycledView() {
        binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = contactsAdapter
        }
        lifecycleScope.launch {
            try {
                val users = contactsFragmentViewModel.getUser()
                contactsAdapter.submitList(users)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun configNavController() {
        binding?.fab?.setOnClickListener {
            findNavController().navigate(
                ContactsFragmentDirections.actionContactsFragmentToFormularioContacts()
            )
        }
    }

    override fun observeViewModel() = Unit
    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() {

        fragmentLayoutWithToolbar()
        showToolbar(title = ("Contacts"), showBack = true)
    }
}
