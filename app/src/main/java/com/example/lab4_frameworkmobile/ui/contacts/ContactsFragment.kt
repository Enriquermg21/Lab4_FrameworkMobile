package com.example.lab4_frameworkmobile.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab4_frameworkmobile.data.domain.usecases.GetUserUseCase
import com.example.lab4_frameworkmobile.databinding.FragmentContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.contacts.adapter.ContactsAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactsFragment @Inject constructor(private var getUserUseCase: GetUserUseCase) :
    BaseFragment<FragmentContactsBinding>() {
    private val contactsAdapter = ContactsAdapter()

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
        lifecycleScope.launch {
            try {
                val users = getUserUseCase()
                contactsAdapter.submitList(users)
            } catch (e: Exception) {
                e.printStackTrace()
            }
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
