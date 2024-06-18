package com.example.lab4_frameworkmobile.hilt

import android.app.Application
import com.example.lab4_frameworkmobile.ui.base.BaseProjectApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleHilt {
    @Provides
    @Singleton
    fun provideBaseProjectApplication(application: Application): BaseProjectApplication {
        return application as BaseProjectApplication
    }
}