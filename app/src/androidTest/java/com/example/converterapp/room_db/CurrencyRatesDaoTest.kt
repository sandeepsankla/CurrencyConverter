package com.example.converterapp.room_db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject

@HiltAndroidTest
class CurrencyRatesDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject lateinit var appDatabase: AppDatabase
    @Inject lateinit var currencyRateDao: CurrencyRatesDao

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }



    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertProduct_returnsSingleProduct() = runTest {
        val currencyExchangeRates = CurrencyExchangeRatesDb(1, 2.21, "INR")
        currencyRateDao.insertAll(currencyExchangeRates)
        val result = currencyRateDao.getAll()
        Assert.assertEquals(1, result.size)
    }

    @Test
    fun deleteLocalDb_ReturnEmptyDB() = runTest{
        currencyRateDao.delete()
        val result = currencyRateDao.getAll()
        Assert.assertEquals(0, result.size)
    }


    @After
    fun closeDatabase() {
        appDatabase.close()
    }

}