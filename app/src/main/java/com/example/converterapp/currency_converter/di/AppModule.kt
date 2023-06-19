package com.example.converterapp.currency_converter.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.converterapp.common.AppLogger
import com.example.converterapp.common.DataStorePreference
import com.example.converterapp.common.PreferenceManager
import com.example.converterapp.currency_converter.data.remote.CurrencyConverterAPI
import com.example.converterapp.currency_converter.data.repo.CurrencyConverterRepo
import com.example.converterapp.currency_converter.data.repo.CurrencyConverterRepoImpl
import com.example.converterapp.room_db.AppDatabase
import com.example.converterapp.room_db.CurrencyRatesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
//import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConverterRepository(api: CurrencyConverterAPI, dao : CurrencyRatesDao, dsPreference: PreferenceManager): CurrencyConverterRepo {
        return CurrencyConverterRepoImpl(api, dao, dsPreference)
    }

    @Provides
    @Singleton
    fun provideDataPreference(@ApplicationContext context: Context): DataStorePreference {
        return DataStorePreference(context)
    }
    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManager(context)
    }
}