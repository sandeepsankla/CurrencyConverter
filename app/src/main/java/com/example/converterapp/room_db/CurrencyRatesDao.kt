package com.example.converterapp.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyRatesDao  {
    @Query("SELECT * FROM CurrencyExchangeRatesDb")
    fun getAll(): List<CurrencyExchangeRatesDb>

    @Insert
    fun insertAll(vararg users: CurrencyExchangeRatesDb)

    @Query("delete FROM CurrencyExchangeRatesDb")
    fun delete()
}