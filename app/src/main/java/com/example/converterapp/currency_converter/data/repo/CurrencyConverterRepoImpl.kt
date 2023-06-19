package com.example.converterapp.currency_converter.data.repo

import com.example.converterapp.BuildConfig
import com.example.converterapp.common.Constant.KEY_APP_ID
import com.example.converterapp.common.Constant.KEY_EXPIRY_TIME
import com.example.converterapp.common.Constant.Thirty_Minutes_InMillis
import com.example.converterapp.common.PreferenceManager
import com.example.converterapp.currency_converter.data.remote.CurrencyConverterAPI
import com.example.converterapp.currency_converter.domain.CurrencyRates
import com.example.converterapp.room_db.CurrencyExchangeRatesDb
import com.example.converterapp.room_db.CurrencyRatesDao
import com.example.converterapp.room_db.toExternalModel

import javax.inject.Inject


class CurrencyConverterRepoImpl @Inject constructor(
    private val api: CurrencyConverterAPI,
    private val dao : CurrencyRatesDao,
    private val preference: PreferenceManager
) : CurrencyConverterRepo {
   private val TAG = CurrencyConverterRepoImpl::class.java.simpleName

    override suspend fun getAllCurrencyList(): ArrayList<CurrencyRates>  {
            val currentValue = System.currentTimeMillis()
            val list = arrayListOf<CurrencyRates>()
            if(isLocalDataExpired(currentValue)){
                val res =  api.getCurrencyList(getQueryMap())
                if(res.rates != null){
                    dao.delete()
                    res.rates.forEach { (key, value) ->
                        val rates = CurrencyExchangeRatesDb(rates = value, country = key, id = null)
                        dao.insertAll(rates)
                        list.add(rates.toExternalModel())
                    }
                    saveLocalDbExpiryTime(currentValue)
                }
            }else{
               val currencyExchangeRatesDbList = dao.getAll()
               for(item in currencyExchangeRatesDbList) {
                   list.add(item.toExternalModel())
               }
            }
            return list
        }


    private fun getQueryMap(): HashMap<String, String>{
        val map = hashMapOf<String, String>()
        map[KEY_APP_ID] = BuildConfig.APP_ID
        return map
    }


    private fun isLocalDataExpired(currentTime :Long): Boolean{
        val localDbExpiryTime = preference.getExpiryTime(KEY_EXPIRY_TIME)
        return  (localDbExpiryTime==0L || localDbExpiryTime < currentTime)
    }

    private fun saveLocalDbExpiryTime(currentTime:Long){
        preference.saveExpiryTime(KEY_EXPIRY_TIME, currentTime.plus(
            Thirty_Minutes_InMillis))
    }
}