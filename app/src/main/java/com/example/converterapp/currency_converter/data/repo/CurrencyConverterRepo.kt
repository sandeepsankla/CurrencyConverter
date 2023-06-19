package com.example.converterapp.currency_converter.data.repo

import com.example.converterapp.currency_converter.domain.CurrencyRates

interface CurrencyConverterRepo {
    suspend fun getAllCurrencyList():ArrayList<CurrencyRates>
}