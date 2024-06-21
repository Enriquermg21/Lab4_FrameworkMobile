package com.example.lab4_frameworkmobile.ui.userdata

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.lab4_frameworkmobile.databinding.FragmentUserDataBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserData : BaseFragment<FragmentUserDataBinding>() {
    private val userDataViewModel: UserDataViewModel by viewModels()
    override fun inflateBinding() {
        binding = FragmentUserDataBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        userData()
    }

    private fun userData() {
        val args: UserDataArgs by navArgs()
        val userName = args.userName
        userDataViewModel.loadUserByName(userName)
        userDataViewModel.userFull.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding?.etName?.setText(user.name)
                binding?.etBirthDate?.setText(user.dateOfBirth)
                binding?.etFavouriteColor?.setText(user.color)
                binding?.etFavoriteCity?.setText(user.favoriteCity)
                binding?.etFavoriteNumber?.setText(user.favoriteNumber)
            } else {
                Log.e(TAG, "User not found for name: $userName")
            }
        }
    }

    private fun updateUser() {
        TODO()
        val name = binding?.etName?.text.toString()
        val dateOfBirth = binding?.etBirthDate?.text.toString()
        val color = binding?.etFavouriteColor?.text.toString()
        val favoriteCity = binding?.etFavoriteCity?.text.toString()
        val favoriteNumber = binding?.etFavoriteNumber?.text.toString()
        userDataViewModel.updateUser(name, dateOfBirth, color, favoriteCity, favoriteNumber)
    }
    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() = Unit
}