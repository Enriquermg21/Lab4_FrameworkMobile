package com.example.lab4_frameworkmobile.ui.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_frameworkmobile.databinding.FragmentContactsBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.contacts.adapter.ContactsAdapter
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsFragment : BaseFragment<FragmentContactsBinding>() {
    private val contactsAdapter = ContactsAdapter()
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
        setupSearchView()
    }

    private fun setupSearchView() {
        binding?.svToolbarSearch?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterUsers(newText.orEmpty())
                return true
            }
        })
    }

    private fun filterUsers(query: String) {
        lifecycleScope.launch {
            try {
                val users = contactsFragmentViewModel.getUser()
                val filteredUsers = if (query.isEmpty()) {
                    users
                } else {
                    users.filter { it.name.contains(query, ignoreCase = true) }
                }
                contactsAdapter.submitList(filteredUsers)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun configRecycledView() {
        binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = contactsAdapter

            // Configurar el ItemTouchHelper para el deslizamiento
            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val deletedUser = contactsAdapter.getUserAtPosition(position)

                    lifecycleScope.launch {
                        try {
                            contactsFragmentViewModel.deleteUser(deletedUser)
                            lifecycleScope.launch {
                                try {
                                    val users = contactsFragmentViewModel.getUser()
                                    Log.d(TAG, "Users fetched from ViewModel: $users")
                                    contactsAdapter.submitList(users)
                                } catch (e: Exception) {
                                    Log.e(TAG, "Error fetching users from ViewModel", e)
                                    e.printStackTrace()
                                }
                            }
                            Log.d(TAG, "User deleted: $deletedUser")
                        } catch (e: Exception) {
                            Log.e(TAG, "Error deleting user", e)
                            e.printStackTrace()
                        }
                    }
                }
            }

            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
            itemTouchHelper.attachToRecyclerView(this)
        }
        lifecycleScope.launch {
            try {
                val users = contactsFragmentViewModel.getUser()
                Log.d(TAG, "Users fetched from ViewModel: $users")
                contactsAdapter.submitList(users)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching users from ViewModel", e)
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
