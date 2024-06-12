package com.example.lab4_frameworkmobile.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab4_frameworkmobile.R
import com.example.lab4_frameworkmobile.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflarVista(inflater)
    }
    private fun inflarVista(layoutInflater: LayoutInflater): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        setupListeners()
    }

    private fun setupListeners() {
        binding.floatingButtonMain.setOnClickListener(this::onClick)
    }

    private fun onClick(view: View?) {
        when (view?.id) {
            R.id.floatingButtonMain -> {
                //  findNavController().navigate(DashboardFragment())
            }
        }
    }
}