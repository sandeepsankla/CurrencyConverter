package com.example.converterapp.room_db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [DataBaseModule::class])
@Module

class DataBaseModuleTest {
    @Singleton
    @Provides
    fun provideCurrencyRatesDao(appDatabase: AppDatabase): CurrencyRatesDao {
        return appDatabase.CurrencyRatesDao()
    }


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}