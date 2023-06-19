package com.example.converterapp.common

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceManager @Inject constructor(@ApplicationContext context: Context)  {

     private  val PREF_NAME = "com.settings.data"
     private var sharedPreferences: SharedPreferences
     init {
       sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
     }

     fun saveExpiryTime(key :String, time :Long) {
         try {
             sharedPreferences.edit().putLong(key, time).apply()
         } catch (e: Exception) {
             Log.e("sasa","Cannot write in SharedPreferences: $e")
         }
     }

     fun getExpiryTime(key :String,): Long {
         var returnValue: Long = 0
         try {
             returnValue = sharedPreferences.getLong(key, 0)
         } catch (e: Exception) {
             Log.e("sasa","Cannot read from SharedPreferences: $e")
         }

         return returnValue
     }
}