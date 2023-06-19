package com.example.converterapp.currency_converter.domain

import com.example.converterapp.currency_converter.data.repo.CurrencyConverterRepo
import com.example.converterapp.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/** fetching the currency list from repository and changes to specific format of ui data */

class GetCurrencyListUseCase @Inject constructor(
        private val repository: CurrencyConverterRepo
    ) {
    suspend operator fun invoke(): Resource<ArrayList<CurrencyRates>?> =
        withContext(Dispatchers.IO){
            val currencyListResponse = repository.getAllCurrencyList()
            if(currencyListResponse.isEmpty()){
               return@withContext Resource.Error("Unable to fetch data. Please try again")
            }else{
                return@withContext Resource.Success(currencyListResponse)
            }
        }

}