package com.example.converterapp.currency_converter.domain

import javax.inject.Inject

data class UseCaseDataClass @Inject constructor(
    val currencyConversionUseCase :CurrencyConversionUseCase,
    val getCurrencyListUseCase :GetCurrencyListUseCase

)
