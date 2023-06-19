package com.example.converterapp.currency_converter.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.converterapp.R
import com.example.converterapp.common.*
import com.example.converterapp.currency_converter.domain.CurrencyRates
import com.example.converterapp.databinding.ActivityMainBinding
import com.example.converterapp.network.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private val TAG  = MainActivity::class.java.simpleName

    /** for logging  */
   @Inject
   lateinit var log :AppLogger
   private val viewModel: CurrencyConverterViewModel by viewModels()
   private var dataBinding : ActivityMainBinding? = null
    /** custom adapter for spinner */
   @Inject lateinit var adapter :CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addObserver()
        dataBinding?.recyclerView?.adapter = adapter
        dataBinding?.recyclerView?.layoutManager = GridLayoutManager(this, 3)
    }

    private fun addObserver() {
        viewModel.currencyList.observe(this){
            when(it){
                is Resource.Success->{
                    it.data?.let { it1 -> showReasonAdaptor(it1) }
                }
                is Resource.Loading ->{
                    log.debugLog(TAG, "loading state")
                }
                is Resource.Error ->{
                    dataBinding?.etCurrency?.showSnackBar( it.message ?: "some thing went wrong")
                }
            }
        }
        viewModel.currencyConvertedList.observe(this){
            when(it){
                is Resource.Success->{
                    dataBinding?.progressBar?.hide()
                    it.data?.let { it1 ->
                        adapter.updateList(it1)
                        adapter.notifyDataSetChanged()
                    }

                }
                is Resource.Loading ->{
                    dataBinding?.progressBar?.show()
                }
                is Resource.Error ->{
                    dataBinding?.progressBar?.hide()
                    dataBinding?.etCurrency?.showSnackBar( it.message ?: "some thing went wrong")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        /** to fetch the currency list */
        viewModel.getCurrencyList()
    }

    private fun showReasonAdaptor(data: ArrayList<CurrencyRates>) {
        val itemAtZero = CurrencyRates(0.0, getString(R.string.select_country))
        data.add(0,itemAtZero)
        val countryListAdapter = CurrencyListAdapter(data)
        dataBinding?.spinner?.adapter = countryListAdapter
        dataBinding?.spinner?.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                Utility.hideKeyboard(this@MainActivity)
                if(index == 0) {
                    dataBinding?.etCurrency?.showSnackBar( getString(R.string.error_message_valid_input))
                } else if(index > 0) {
                    if(dataBinding?.etCurrency?.text.isNullOrEmpty()){
                        dataBinding?.etCurrency?.showSnackBar( getString(R.string.error_message_enter_amount))
                        return
                    }
                    /** updating the currency rate as per the user input */
                   viewModel.convertCurrency(index-1, dataBinding?.etCurrency?.text.toString().toDouble(), data)
                }

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

}