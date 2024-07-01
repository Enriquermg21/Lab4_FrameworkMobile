package com.example.lab4_frameworkmobile.ui.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseProjectApplication @Inject constructor() : Application() {

    override fun onCreate() {
        super.onCreate()
    }


}