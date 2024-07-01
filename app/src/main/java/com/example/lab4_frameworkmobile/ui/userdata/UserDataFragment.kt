package com.example.lab4_frameworkmobile.ui.userdata

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.databinding.FragmentUserDataBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDataFragment : BaseFragment<FragmentUserDataBinding>() {
    private val userDataViewModel: UserDataViewModel by viewModels()
    private var idUser: Int = 0

    override fun inflateBinding() {
        binding = FragmentUserDataBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        userData()
        configEditText()
        configMapNavController()
        binding?.btnUpdate?.setOnClickListener {
            updateUser()
            configNavController()
        }
    }

    private fun configNavController() {
        findNavController().navigate(
            UserDataFragmentDirections.actionUserDataToContactsFragment()
        )
    }

    private fun configMapNavController() {
        binding?.btnMapFavoriteCity?.setOnClickListener {
            findNavController().navigate(
                UserDataFragmentDirections.actionUserDataToMapFragment()
            )
        }
    }


    private fun userData() {
        val args: UserDataFragmentArgs by navArgs()
        val userName = args.userName
        Log.d(TAG, "userData called with userName: $userName")
        userDataViewModel.loadUserByName(userName)
        userDataViewModel.userFull.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                idUser = user.id
                Log.d(TAG, "User found: $user")
                binding?.etName?.setText(user.name)
                binding?.etBirthDate?.setText(user.dateOfBirth)
                binding?.etFavouriteColor?.setText(user.color)
                binding?.etFavoriteCity?.setText(user.favoriteCity)
                binding?.etFavoriteNumber?.setText(user.favoriteNumber)
                binding?.etLocation?.setText(user.currentLocation)
            } else {
                Log.e(TAG, "User not found for name: $userName")
            }
        }
    }

    private fun updateUser() {
        Log.d(TAG, "updateUser called")
        val name = binding?.etName?.text.toString()
        val dateOfBirth = binding?.etBirthDate?.text.toString()
        val color = binding?.etFavouriteColor?.text.toString()
        val favoriteCity = binding?.etFavoriteCity?.text.toString()
        val favoriteNumber = binding?.etFavoriteNumber?.text.toString()
        val currentLocation = binding?.etLocation?.text.toString()
        val updatedUser = UserEntity(
            id = idUser,
            name = name,
            dateOfBirth = dateOfBirth,
            color = color,
            favoriteCity = favoriteCity,
            favoriteNumber = favoriteNumber,
            currentLocation = currentLocation
        )
        Log.d(TAG, "Updated user details: $updatedUser")

        userDataViewModel.updateUser(updatedUser)
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
    }


    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() {
        fragmentLayoutWithToolbar()
        showToolbar(title = "Edit ", showBack = true)
    }
}