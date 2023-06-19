package com.example.converterapp.currency_converter.domain

import com.example.converterapp.currency_converter.data.CurrencyRatesResponse
import com.example.converterapp.currency_converter.data.remote.CurrencyConverterAPI
import com.example.converterapp.currency_converter.data.repo.CurrencyConverterRepo
import com.example.converterapp.currency_converter.data.repo.CurrencyConverterRepoImpl
import com.example.converterapp.currency_converter.presentation.CurrencyConverterViewModel
import com.example.converterapp.network.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class GetCurrencyListUseCaseTest{
    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    // Mock dependencies
    @Mock
    lateinit var mockRepository: CurrencyConverterRepoImpl

    // Class under test
    private lateinit var useCase: GetCurrencyListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        hiltRule.inject()
        useCase = GetCurrencyListUseCase(mockRepository)
    }

    @Test
    fun `invoke - success case`() = runTest {
        // Arrange
        val mockCurrencyList = ArrayList<CurrencyRates>()
        mockCurrencyList.add(CurrencyRates(1.1, "INR"))
        Mockito.`when`(mockRepository.getAllCurrencyList()).thenReturn(mockCurrencyList)

        // Act
        val result = useCase.invoke()

        // Assert
        Assert.assertTrue(result is Resource.Success)
        Assert.assertEquals(mockCurrencyList, (result as Resource.Success).data)
    }

    @Test
    fun `invoke - error case`() = runTest {
        // Arrange
        Mockito.`when`(mockRepository.getAllCurrencyList()).thenReturn(arrayListOf())

        // Act
        val result = useCase.invoke()

        // Assert
        Assert.assertTrue(result is Resource.Error)
        Assert.assertEquals("Unable to fetch data. Please try again", (result as Resource.Error).message)
    }

}
