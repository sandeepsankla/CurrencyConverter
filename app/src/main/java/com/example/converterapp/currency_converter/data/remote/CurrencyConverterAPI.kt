package com.example.converterapp.currency_converter.data.remote

import com.example.converterapp.currency_converter.data.CurrencyRatesResponse
import com.example.converterapp.currency_converter.domain.CurrencyRates
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CurrencyConverterAPI {
    @GET("api/latest.json")
    suspend fun getCurrencyList(@QueryMap queryMap: HashMap<String, String>): CurrencyRatesResponse
}