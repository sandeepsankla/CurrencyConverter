package com.example.converterapp.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.converterapp.currency_converter.domain.CurrencyRates
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CurrencyExchangeRatesDb")
data class CurrencyExchangeRatesDb(
 @PrimaryKey(autoGenerate = true) val id : Long? = null,
 val rates :Double ? =null,
 val country: String? = null
)

fun CurrencyExchangeRatesDb.toExternalModel()  = CurrencyRates(
 rates = rates ?: 0.0,
 country = country ?: ""
)
