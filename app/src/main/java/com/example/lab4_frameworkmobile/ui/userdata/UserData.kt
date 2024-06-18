package com.example.lab4_frameworkmobile.ui.userdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab4_frameworkmobile.databinding.FragmentUserDataBinding
import com.example.lab4_frameworkmobile.ui.base.BaseFragment

class UserData : BaseFragment<FragmentUserDataBinding>() {
    override fun inflateBinding() {
        binding = FragmentUserDataBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {

    }

    override fun observeViewModel() = Unit

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) =
        Unit

    override fun configureToolbarAndConfigScreenSections() = Unit
}