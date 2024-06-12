package com.example.lab4_frameworkmobile.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lab4_frameworkmobile.databinding.ActivityUserDetailBinding

class UserDetailFragment : Fragment() {

    private lateinit var bindingDetail: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetail = ActivityUserDetailBinding.inflate(layoutInflater)
    }
}
