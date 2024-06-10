package com.example.lab4_frameworkmobile.ui.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4_frameworkmobile.R
import com.example.lab4_frameworkmobile.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var bindingDetail: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        bindingDetail = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)
    }
}
