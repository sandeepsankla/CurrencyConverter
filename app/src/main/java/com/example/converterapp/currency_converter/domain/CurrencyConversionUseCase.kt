package com.example.converterapp.currency_converter.domain

import com.example.converterapp.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
/** conversion logic is written here */

class CurrencyConversionUseCase @Inject constructor() {
    suspend operator fun invoke(
        index: Int,
        amount: Double,
        data: ArrayList<CurrencyRates>
    ): Resource<ArrayList<CurrencyRates>?> =

        withContext(Dispatchers.Default) {
            if (amount == 0.0) {
               return@withContext Resource.Error("Please enter a valid amount")
               // return@withContext
            }

            val selectedItem = data[index]
            val conversionFactor = amount / selectedItem.rates
            val result = ArrayList(data)
            for (item in data) {
                var rate = item.rates * conversionFactor
                rate = Math.round(rate * 1000.0) / 1000.0
                val currencyRates = CurrencyRates(rate, item.country)
                result.add(currencyRates)
            }
            Resource.Success(result)
        }
}
