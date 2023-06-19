package com.example.converterapp.currency_converter.presentation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.converterapp.currency_converter.domain.CurrencyRates
import com.example.converterapp.currency_converter.domain.GetCurrencyListUseCase
import com.example.converterapp.currency_converter.domain.UseCaseDataClass
import com.example.converterapp.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val dataClass: UseCaseDataClass
) : ViewModel() {

    private var _currencyList: MutableLiveData<Resource<ArrayList<CurrencyRates>?>> = MutableLiveData()
    val currencyList: LiveData<Resource<ArrayList<CurrencyRates>?>> get() = _currencyList

    private var _currencyConvertedList: MutableLiveData<Resource<ArrayList<CurrencyRates>?>> = MutableLiveData()
    val currencyConvertedList: LiveData<Resource<ArrayList<CurrencyRates>?>> get() = _currencyConvertedList

    fun getCurrencyList() {
        viewModelScope.launch {
            _currencyList.postValue(Resource.Loading())
          val response =    dataClass.getCurrencyListUseCase()
            _currencyList.postValue(response)
        }
    }

    fun convertCurrency(i: Int, amount: Double, data: ArrayList<CurrencyRates>) {
        viewModelScope.launch {
            _currencyConvertedList.postValue(Resource.Loading())
            data.removeAt(0)
            val response = dataClass.currencyConversionUseCase(i, amount, data)
            _currencyConvertedList.postValue(response)
        }
    }

}