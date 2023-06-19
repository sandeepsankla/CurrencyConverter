package com.example.converterapp.common

import com.example.converterapp.BuildConfig
import timber.log.Timber
import javax.inject.Inject

class AppLogger @Inject constructor(){


     init {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun infoLog(tag:String,infoText:String?){
        Timber.tag(tag).i(infoText)
    }


    fun debugLog(tag:String,debugText:String?){
        Timber.tag(tag).d(debugText)
    }


    fun errorLog(tag:String,errorText:String?){
        Timber.tag(tag).e(errorText)
    }

    fun warningLog(tag:String,warningText:String?){
        Timber.tag(tag).w(warningText)
    }

    fun logCurrentMethodName(tag:String){
        Timber.tag(tag).i(getCurrentMethodName())
    }

    /**
     *  get the name of current method name
     */
    private fun getCurrentMethodName():String{
        return Thread.currentThread().stackTrace[4].methodName
    }

}