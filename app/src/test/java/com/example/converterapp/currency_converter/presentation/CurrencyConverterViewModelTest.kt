package com.example.converterapp.currency_converter.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import com.example.converterapp.currency_converter.domain.UseCaseDataClass
import com.example.converterapp.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CurrencyConverterViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // Executes coroutines in a deterministic way.
    //private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testDispatcher = StandardTestDispatcher()
    // Allows us to pause and resume coroutines during testing.
   // private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: CurrencyConverterViewModel
    @Mock lateinit var dataClass: UseCaseDataClass

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = CurrencyConverterViewModel(dataClass)
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun test_getData() = runTest {
        Mockito.`when`(dataClass.getCurrencyListUseCase()).thenReturn(Resource.Success(arrayListOf()))
        val sut =  CurrencyConverterViewModel(dataClass)
        sut.getCurrencyList()
        testDispatcher.scheduler.advanceUntilIdle()
        val result1 = sut.currencyList.value
        Assert.assertEquals(true, result1 is Resource.Success)
    }


   /* @Before
    fun setup() {
        dataClass = mockk()
        viewModel = CurrencyConverterViewModel(dataClass)

        // Replace the ViewModel's dispatcher with the test dispatcher.
        viewModel.viewModelScope = testCoroutineScope
    }*/

   /* @After
    fun tearDown() {
        // Clean up after the test.
        testCoroutineScope.cleanupTestCoroutines()
    }*/

    /*@Test
    fun `getCurrencyList should emit Resource Loading and Success`() {
        // Create a mock observer to verify the emitted values.
        val observer = mockk<Observer<Resource<ArrayList<CurrencyRates>>>>()
        viewModel.currencyList.observeForever(observer)

        // Set up the mock response for the UseCaseDataClass method.
        val mockResponse = Resource.Success(arrayListOf(CurrencyRates(1.0, "USD" )))
        coEvery { dataClass.getCurrencyListUseCase() } returns mockResponse

        // Call the function being tested.
        viewModel.getCurrencyList()

        // Advance the dispatcher to execute coroutines until they are idle.
        testCoroutineDispatcher.advanceUntilIdle()

        // Verify that the loading state is emitted first.
        coVerify { observer.onChanged(Resource.Loading()) }

        // Verify that the success state with the mock response is emitted.
        coVerify { observer.onChanged(mockResponse) }

        // Clean up the observer.
        viewModel.currencyList.removeObserver(observer)
    }*/
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}
