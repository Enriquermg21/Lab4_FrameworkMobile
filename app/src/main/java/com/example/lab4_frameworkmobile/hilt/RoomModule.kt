package com.example.lab4_frameworkmobile.hilt

import android.content.Context
import androidx.room.Room
import com.example.lab4_frameworkmobile.data.database.UserDatabase
import com.example.lab4_frameworkmobile.data.domain.usecases.InsertUserUseCase
import com.example.lab4_frameworkmobile.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val QUOTE_DATABASE_NAME = "quote_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDatabase::class.java, QUOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(database: UserDatabase) = database.quoteDao()

    @Singleton
    @Provides
    fun provideInsertUserUseCase(userRepository: UserRepository): InsertUserUseCase {
        return InsertUserUseCase(userRepository)
    }
}