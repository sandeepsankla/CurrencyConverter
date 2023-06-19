package com.example.converterapp.currency_converter.data.repo

import com.example.converterapp.common.PreferenceManager
import com.example.converterapp.currency_converter.data.remote.CurrencyConverterAPI
import com.example.converterapp.room_db.CurrencyRatesDao
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CurrencyConverterRepoImplTest {

    @Mock
    lateinit var api: CurrencyConverterAPI
    @Mock
    lateinit var dao: CurrencyRatesDao
    @Mock
    lateinit var preference: PreferenceManager


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun testGetCurrencyConvertedData_EmptyList() = runTest{
         Mockito.`when`(api.getCurrencyList(hashMapOf())).thenReturn(null)

       // val sut =
    }
}