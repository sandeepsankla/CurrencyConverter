package com.example.converterapp.currency_converter.domain

data class CurrencyRates(
    var rates :Double,
    val country: String
){
    fun getDisplayText():String{
        return "$country \n$rates"
    }
}
