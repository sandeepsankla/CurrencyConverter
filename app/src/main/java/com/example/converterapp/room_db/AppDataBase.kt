package com.example.converterapp.room_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyExchangeRatesDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CurrencyRatesDao(): CurrencyRatesDao
}